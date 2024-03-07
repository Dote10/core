package com.spring.core.app.v5;


import com.spring.core.logTrace.LogTrace;
import com.spring.core.trace.callback.TraceTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private  final TraceTemplate traceTemplate;

    public OrderRepositoryV5(LogTrace trace) {
        this.traceTemplate = new TraceTemplate(trace);
    }

    public void save(String itemId){

        traceTemplate.execute("OrderRepository.save()",()->{
            if(itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            //상품을 저장하는데 1초 정도 걸린다고 가정
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
