package controllers;

import com.sun.net.httpserver.HttpExchange;
import models.Cliente;
import repositories.ClienteRepository;
import utils.HttpUtils;
import views.ClienteView;
import views.HomeView;

import java.util.List;
import java.util.Map;

public class ClienteController {

    // HOME
    public static void home(HttpExchange exchange) {

        try {

            String html = HomeView.home();

            HttpUtils.responder(exchange, html);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // LISTAR CLIENTES
    public static void listar(HttpExchange exchange) {

        try {

            List<Cliente> clientes = ClienteRepository.todos();

            String html = ClienteView.lista(clientes);

            HttpUtils.responder(exchange, html);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // FORM NOVO
    public static void formNovo(HttpExchange exchange) {

        try {

            String html = ClienteView.formNovo();

            HttpUtils.responder(exchange, html);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // GUARDAR
    public static void guardar(HttpExchange exchange) {

        try {

            if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {

                exchange.sendResponseHeaders(405, -1);
                return;
            }

            Map<String, String> dados = HttpUtils.lerFormulario(exchange);

            Cliente cliente = new Cliente();

            cliente.setNome(dados.get("nome"));
            cliente.setEmail(dados.get("email"));
            cliente.setTelefone(dados.get("telefone"));

            ClienteRepository.guardar(cliente);

            String html = ClienteView.sucessoGuardar();

            HttpUtils.responder(exchange, html);

        } catch (Exception e) {

            e.printStackTrace();

            HttpUtils.erro(exchange, "Erro ao guardar cliente");
        }
    }


    // FORM EDITAR
    public static void formEditar(HttpExchange exchange) {

        try {

            String query = exchange.getRequestURI().getQuery();

            int id = Integer.parseInt(
                    query.split("=")[1]
            );

            Cliente cliente = ClienteRepository.buscarPorId(id);

            String html = ClienteView.formEditar(cliente);

            HttpUtils.responder(exchange, html);

        } catch (Exception e) {

            e.printStackTrace();

            HttpUtils.erro(exchange, "Erro ao carregar cliente");
        }
    }


    // ATUALIZAR
    public static void atualizar(HttpExchange exchange) {

        try {

            if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                exchange.sendResponseHeaders(405, -1);
                return;
            }

            Map<String, String> dados = HttpUtils.lerFormulario(exchange);

            Cliente cliente = new Cliente();

            cliente.setId(
                    Integer.parseInt(dados.get("id"))
            );

            cliente.setNome(dados.get("nome"));
            cliente.setEmail(dados.get("email"));
            cliente.setTelefone(dados.get("telefone"));

            ClienteRepository.atualizar(cliente);

            exchange.getResponseHeaders().add("Location", "/clientes");
            

            exchange.sendResponseHeaders(302, -1);

            exchange.close();

        } catch (Exception e) {

            e.printStackTrace();

            HttpUtils.erro(exchange, "Erro ao atualizar cliente");
        }
    }


    // APAGAR
    public static void apagar(HttpExchange exchange) {

        try {

            String query = exchange.getRequestURI().getQuery();

            int id = Integer.parseInt(
                    query.split("=")[1]
            );

            ClienteRepository.apagar(id);

            exchange.getResponseHeaders().add("Location", "/clientes");

            exchange.sendResponseHeaders(302, -1);

            exchange.close();

        } catch (Exception e) {

            e.printStackTrace();

            HttpUtils.erro(exchange, "Erro ao apagar cliente");
        }
    }
}