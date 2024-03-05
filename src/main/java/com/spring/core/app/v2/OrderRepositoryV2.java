package com.spring.core.app.v2;


import com.spring.core.trace.TraceId;
import com.spring.core.trace.TraceStatus;
import com.spring.core.trace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId){

        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId,"OrderRepository.save()");
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생!");
            }

            //상품을 저장하는데 1초 정도 걸린다고 가정
            sleep(1000);

            trace.end(status);
        }catch (Exception e){
            trace.exception(status,e);
            throw e;
        }



    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
