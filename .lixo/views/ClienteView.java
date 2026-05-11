package views;

import models.Cliente;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ClienteView {




    // LISTA
    public static String lista(List<Cliente> clientes) throws Exception {

        String html = Files.readString(
                Path.of("templates/clientes.html")
        );

        StringBuilder linhas = new StringBuilder();

        for (Cliente c : clientes) {

            linhas.append("<tr>");

            linhas.append("<td>").append(c.getId()).append("</td>");

            linhas.append("<td>").append(c.getNome()).append("</td>");

            linhas.append("<td>").append(c.getEmail()).append("</td>");

            linhas.append("<td>").append(c.getTelefone()).append("</td>");

            linhas.append("<td>");

            linhas.append("""
                <a href='/editar?id=
            """);

            linhas.append(c.getId());

            linhas.append("'>Editar</a>");

            linhas.append("""
                <a href='/apagar?id=
            """);

            linhas.append(c.getId());

            linhas.append("""
                ' onclick="return confirm('Apagar?')">
                Apagar
                </a>
            """);

            linhas.append("</td>");

            linhas.append("</tr>");
        }

        return html.replace(
                "{{linhas}}",
                linhas.toString()
        );
    }


    // FORM NOVO
    public static String formNovo() throws Exception {
        return Files.readString(
                Path.of("templates/novo.html")
        );
    }


    // SUCESSO GUARDAR
    public static String sucessoGuardar() throws Exception {
        return Files.readString(
                Path.of("templates/sucesso.html")
        );
    }


    // FORM EDITAR
    public static String formEditar(Cliente cliente) throws Exception {

        String html = Files.readString(
                Path.of("templates/editar.html")
        );

        html = html.replace(
                "{{id}}",
                String.valueOf(cliente.getId())
        );

        html = html.replace(
                "{{nome}}",
                cliente.getNome()
        );

        html = html.replace(
                "{{email}}",
                cliente.getEmail()
        );

        html = html.replace(
                "{{telefone}}",
                cliente.getTelefone()
        );

        return html;
    }
}