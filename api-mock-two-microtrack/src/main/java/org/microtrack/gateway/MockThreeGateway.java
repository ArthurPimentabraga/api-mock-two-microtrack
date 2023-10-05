package org.microtrack.gateway;

import org.microtrack.model.dto.ProductDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MockThreeGateway {

    public String callMockThree(ProductDTO dto) throws IOException, InterruptedException {
        try {

            String uri = "http://localhost:8082/status-update";

            System.out.println(uri);

            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(dto.getString()))
                    .build();

            HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body().toString();

        } catch (Exception exception) {
            System.out.print("Erro na request para a API three!");
            throw exception;
        }

    }

}
