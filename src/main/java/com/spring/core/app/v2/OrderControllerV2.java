package com.spring.core.app.v2;

import com.spring.core.trace.TraceStatus;
import com.spring.core.trace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/order")
    public String createOrder(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.createOrder()");
            orderService.orderItem(status.getTraceId(),itemId);
            trace.end(status);
            return "ok";
        }catch (Exception e){
            trace.exception(status,e);
            throw e; //예외를 꼭 다시 던저주어야 한다.
        }

    }

}
