package com.spring.core.app.v4;

import com.spring.core.logTrace.LogTrace;
import com.spring.core.trace.TraceStatus;
import com.spring.core.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {
    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    @GetMapping("/v4/order")
    public String createOrder(String itemId){
        AbstractTemplate<String> template = new AbstractTemplate<>(trace) {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };

      return  template.execute("OrderController.createOrder()");

    }

}
