package org.microtrack.service;

import lombok.AllArgsConstructor;
import org.microtrack.model.dto.MockTwoBodyDTO;
import org.microtrack.dto.ResponseTrace;
import org.microtrack.dto.Trace;

import java.io.IOException;
import java.sql.Timestamp;

@AllArgsConstructor
public class MockTwoService {

    public void test(MockTwoBodyDTO body) throws IOException, InterruptedException {
        // TODO Criar Beans para cada objeto singleton
        Manager manager = new Manager(true);
        TraceService traceService = new TraceService();

        Trace trace = Trace.builder()
                .traceId(body.getTraceId())
                .serviceName("api-mock-two-microtrack")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .checkpointName("Test dinâmico two")
                .isError(Boolean.TRUE)
                .build();

        ResponseTrace response = traceService.checkpoint(manager, trace);

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Message: " + response.getMessage());

        System.out.println(trace.getAllString()); // TODO Melhorar body
    }
}
