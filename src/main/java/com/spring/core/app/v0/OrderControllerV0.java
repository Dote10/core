package com.spring.core.app.v0;

import com.spring.core.trace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0 {
    private final OrderServiceV0 orderService;
    private final HelloTraceV1 helloTrace;

    @GetMapping("/v0/order")
    public String createOrder(String itemId){
            orderService.orderItem(itemId);
        return "ok";
    }

}
