package com.spring.core.app.v0_1;



import com.spring.core.trace.TraceId;
import com.spring.core.trace.TraceStatus;
import com.spring.core.trace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV0_1 {

    private final OrderRepositoryV0_1 orderRepository;
    private final HelloTraceV1 helloTrace;

    public void orderItem(String itemId,TraceId traceId){

        TraceStatus traceStatus = helloTrace.record("orderItem",traceId.createNextId());

        try {
            orderRepository.save(itemId,traceStatus.getTraceId());
        }catch(Exception e){
            helloTrace.exception(traceStatus,e);
            throw e;
        }
        helloTrace.end(traceStatus);
    }
}
