package com.spring.core.app.v4;



import com.spring.core.logTrace.LogTrace;
import com.spring.core.trace.TraceStatus;
import com.spring.core.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId){
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            public Void call() {
               orderRepository.save(itemId);
               return null;
            }
        };
        template.execute("OrderService.orderItem()");
    }
}
