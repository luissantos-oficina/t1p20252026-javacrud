
import com.sun.net.httpserver.HttpServer;

import database.LigacaoBD;
import routes.ClienteRoutes;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(
            new InetSocketAddress(8082), 0
        );

        ClienteRoutes.registar(server);

        server.start();

        System.out.println("Servidor em http://localhost:8080");
    }
}
