package com.spring.core.app.v3;

import com.spring.core.logTrace.LogTrace;
import com.spring.core.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {
    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/order")
    public String createOrder(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.createOrder()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        }catch (Exception e){
            trace.exception(status,e);
            throw e; //예외를 꼭 다시 던저주어야 한다.
        }

    }

}
