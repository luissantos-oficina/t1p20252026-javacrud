package utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    // RESPONDER HTML
    public static void responder(
            HttpExchange exchange,
            String html
    ) throws IOException {

        exchange.getResponseHeaders().set(
                "Content-Type",
                "text/html; charset=UTF-8"
        );

        exchange.sendResponseHeaders(
                200,
                html.getBytes().length
        );

        OutputStream os =
                exchange.getResponseBody();

        os.write(html.getBytes());

        os.close();
    }


    // PÁGINA DE ERRO
    public static void erro(
            HttpExchange exchange,
            String mensagem
    ) {

        try {

            String html = """
                <html>
                <head>
                    <meta charset="UTF-8">
                </head>
                <body>

                <h2>Erro</h2>

                <p>
            """ + mensagem + """
                </p>

                <a href='/'>
                    Voltar
                </a>

                </body>
                </html>
            """;

            responder(exchange, html);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // LER FORMULÁRIO POST
    public static Map<String, String> lerFormulario(
            HttpExchange exchange
    ) throws Exception {

        String body = new String(
                exchange.getRequestBody()
                        .readAllBytes(),
                "UTF-8"
        );

        Map<String, String> dados =
                new HashMap<>();

        String[] params = body.split("&");

        for (String p : params) {

            String[] kv = p.split("=");

            if (kv.length == 2) {

                String chave = kv[0];

                String valor =
                        URLDecoder.decode(
                                kv[1],
                                "UTF-8"
                        );

                dados.put(chave, valor);
            }
        }

        return dados;
    }
}