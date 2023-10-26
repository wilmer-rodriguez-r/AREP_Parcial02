package org.example.mathService;

import org.json.*;

import static spark.Spark.*;

public class LucasServer {

    public static void main(String[] args) {
        initServer();
    }

    private static void initServer() {
        port(getPort());
        get("/hola", (request, response) -> "hola como estas");
        get("/lucasseq", (request, response)-> {
            //Obtener valor del query
            Integer value = Integer.valueOf(request.queryMap().get("value").value());

            //Ejecutar la secuencia
            String sequence = LucasService.listSequence(value);

            //Crear json que retornara el resultado
            JSONObject json = new JSONObject();
            json.accumulate("operation", "Secuencia de Lucas");
            json.accumulate("input", value);
            json.accumulate("output", sequence);

            return json;
        });
    }

    private static int getPort() {
        if (System.getenv("PORTSERVICE") != null) {
            return Integer.parseInt(System.getenv("PORTSERVICE"));
        }
        return 4560;
    }
}
