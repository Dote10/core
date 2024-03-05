package com.spring.core.app.v0_1;

import com.spring.core.trace.TraceStatus;
import com.spring.core.trace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0_1 {
    private final OrderServiceV0_1 orderService;
    private final HelloTraceV1 helloTrace;

    @GetMapping("/v0_1/order")
    public String createOrder(String itemId){
        TraceStatus traceStatus = helloTrace.begin("createOrder");

        try {
            orderService.orderItem(itemId,traceStatus.getTraceId());
        }catch (Exception e){
            helloTrace.exception(traceStatus,e);
           throw e;
        }

        helloTrace.end(traceStatus);

        return "ok";
    }

}
