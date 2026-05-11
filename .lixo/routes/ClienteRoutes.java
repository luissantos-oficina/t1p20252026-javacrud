package routes;

import com.sun.net.httpserver.HttpServer;

import controllers.ClienteController;

public class ClienteRoutes {

    public static void registar(HttpServer server) {

        server.createContext("/", ClienteController::home);

        server.createContext("/clientes", ClienteController::listar);

        server.createContext("/novo", ClienteController::formNovo);

        server.createContext("/guardar", ClienteController::guardar);

        server.createContext("/editar", ClienteController::formEditar);

        server.createContext("/atualizar", ClienteController::atualizar);
    }
}
