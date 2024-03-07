package com.spring.core.app.v5;

import com.spring.core.logTrace.LogTrace;
import com.spring.core.trace.callback.TraceTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {
    private final OrderServiceV5 orderService;
    private final TraceTemplate traceTemplate;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.traceTemplate = new TraceTemplate(trace);
    }

    @GetMapping("/v5/order")
    public String createOrder(String itemId){
       return traceTemplate.execute("OrderController.createOrder()",()-> {
            orderService.orderItem(itemId);
            return "ok";
        });
    }

}
