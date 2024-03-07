package com.spring.core.app.v5;



import com.spring.core.logTrace.LogTrace;
import com.spring.core.trace.callback.TraceTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate traceTemplate;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.traceTemplate = new TraceTemplate(trace);
    }

    public void orderItem(String itemId){
        traceTemplate.execute("OrderService.OrderItem()",()->{
            orderRepository.save(itemId);
            return null;
        });
    }
}
