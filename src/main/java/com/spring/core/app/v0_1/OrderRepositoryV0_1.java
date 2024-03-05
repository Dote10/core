package com.spring.core.app.v0_1;

import com.spring.core.trace.TraceId;
import com.spring.core.trace.TraceStatus;
import com.spring.core.trace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0_1 {

    private final HelloTraceV1 helloTrace;
    public void save(String itemId,TraceId traceId){

        TraceStatus traceStatus = helloTrace.record("save",traceId.createNextId());

        //저장 로직
        if(itemId.equals("ex")){
            helloTrace.exception(traceStatus,new IllegalStateException("예외 발생!"));
            throw new IllegalStateException("예외 발생!");
        }
        //상품을 저장하는데 1초 정도 걸린다고 가정
        sleep(1000);
        helloTrace.end(traceStatus);

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
