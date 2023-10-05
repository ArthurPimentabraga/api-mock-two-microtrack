package org.microtrack.service;

import lombok.AllArgsConstructor;
import org.microtrack.dto.ResponseTrace;
import org.microtrack.dto.Trace;
import org.microtrack.gateway.MockThreeGateway;
import org.microtrack.model.dto.ProductDTO;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;

@AllArgsConstructor
public class MockTwoService {

    public void calculateTotalPayment(ProductDTO body) throws IOException, InterruptedException {
        // TODO Criar Beans para cada objeto singleton
        Manager manager = new Manager(true);
        TraceService traceService = new TraceService();

        ProductDTO product = mockTotalPayment(body);

        try {

            mockErrorTrace();
            registerTrace(body, product, traceService, manager);

        } catch (Exception ex) {
            registerErrorTrace(body, product, traceService, manager);
        }

        callMockThree(product);
    }

    public ProductDTO mockTotalPayment(ProductDTO product) {
        product.setTotalPayment(100.0);
        return product;
    }

    public void mockErrorTrace() throws Exception {
        Random random = new Random();
        if (random.nextBoolean())
            throw new Exception("Error Trace");
    }

    private static void registerTrace(ProductDTO body, ProductDTO product, TraceService traceService, Manager manager) throws IOException, InterruptedException {
        Trace trace = Trace.builder()
                .traceId(body.getId())
                .serviceName("api-mock-two-microtrack")
                .checkpointName("calculo-total-compra")
                .isError(Boolean.FALSE)
                .genericData(product)
                .build();

        ResponseTrace response = traceService.checkpoint(manager, trace);

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Message: " + response.getMessage());
    }

    private static void registerErrorTrace(ProductDTO body, ProductDTO product, TraceService traceService, Manager manager) throws IOException, InterruptedException {
        Trace trace = Trace.builder()
                .traceId(body.getId())
                .serviceName("api-mock-two-microtrack")
                .checkpointName("error-calculo-total-compra")
                .isError(Boolean.TRUE)
                .genericData(product)
                .build();

        ResponseTrace response = traceService.checkpoint(manager, trace);

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Message: " + response.getMessage());
    }

    private static void callMockThree(ProductDTO product) throws IOException, InterruptedException {
        Thread.sleep(5000);
        MockThreeGateway gateway = new MockThreeGateway();
        String responseGateway = gateway.callMockThree(product);
        System.out.println("Requisição à API 3: %n" + responseGateway);
    }

}
