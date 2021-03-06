package ru.kalcho.instagram.server;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import ru.kalcho.instagram.Instagram;
import ru.kalcho.instagram.response.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collections;

/**
 *
 */
public class WebServer {

    private static final String DEFAULT_CHARSET = "UTF-8";

    HttpServer server;

    public void start(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new RequestHandler());
        server.setExecutor(null);
        server.start();
    }

    private static class RequestHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                String response;
                if ("GET".equalsIgnoreCase(exchange.getRequestMethod()) &&
                        "/latest-photo".equals(exchange.getRequestURI().getPath())) {
                    response = latestPhoto();
                    exchange.getResponseHeaders().put("Content-Type", Collections.singletonList("application/json; charset=utf-8"));
                    exchange.sendResponseHeaders(200, response.getBytes(DEFAULT_CHARSET).length);
                } else {
                    response = "404 Not Found";
                    exchange.sendResponseHeaders(404, response.getBytes(DEFAULT_CHARSET).length);
                }
                exchange.getResponseBody().write(response.getBytes(DEFAULT_CHARSET));
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                String response = "500 Server error";
                exchange.sendResponseHeaders(500, response.getBytes(DEFAULT_CHARSET).length);
                exchange.getResponseBody().write(response.getBytes(DEFAULT_CHARSET));
                exchange.getResponseBody().close();
            }
        }

        private String latestPhoto() {
            String clientId = System.getProperty("instagram.client_id");
            String userId = System.getProperty("instagram.user_id");

            Response response = new Instagram(clientId).recentMedia(userId, 1);
            if (response.getData() != null && response.getData().length > 0) {
//                Images images = response.getData()[0].getImages();
                return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                        .toJson(response.getData()[0]);
            }
            return "";
        }

    }

    public static void main(String[] args) throws IOException {
        String portProperty = System.getProperty("http.port");
        Integer port = portProperty != null ? Integer.valueOf(portProperty) : 8000;

        WebServer ws = new WebServer();
        ws.start(port);
    }

}
