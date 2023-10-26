package org.example.proxyService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static spark.Spark.*;

public class ProxyServer {

    private static List<String> backendServices = new ArrayList<>();
    private static AtomicInteger serverBackend = new AtomicInteger(0);

    public static void main(String[] args) {
        initServer();
    }

    public static void initServer() {
        port(getPort());
        getBackendServices();
        get("/", (request, response) -> getHTML());
        get("/index.html", (request, response) -> getHTML());
        get("/lucasseq", (request, response) -> roundRobin(request.queryMap().get("value").value()));
    }

    private static int getPort() {
        if (System.getenv("PORTPROXY") != null) {
            return Integer.parseInt(System.getenv("PORTPROXY"));
        }
        return 5000;
    }

    private static void getBackendServices() {
        if (System.getenv("BACKENDSERVICES") != null) {
            backendServices = Collections.singletonList(System.getenv("BACKENDSERVICES"));
        } else {
            //Localmente
            //backendServices.add("http://localhost:4560");
            //backendServices.add("http://localhost:4561");
            //remote
            backendServices.add("http://ec2-54-210-158-99.compute-1.amazonaws.com:4560");
            backendServices.add("http://ec2-34-235-162-85.compute-1.amazonaws.com:4560");
        }
    }

    private static String roundRobin(String value) throws Exception {
        int server = (serverBackend.addAndGet(1))%backendServices.size();
        String url = backendServices.get(server) + "/lucasseq?value="+value;
        System.out.println(url);
        return HttpConnection.request(url);
    }

    private static String getHTML() {
        return"<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Secuencia Lucas</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Obtener una secuancia de lucas</h1>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"numero\">Valor:</label><br>\n" +
                "            <input type=\"number\" id=\"numero\" name=\"numero\" value=\"12\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let numero = document.getElementById(\"numero\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/lucasseq?value=\"+numero);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "</html>";
    }
}
