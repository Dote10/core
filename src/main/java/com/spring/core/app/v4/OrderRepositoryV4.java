package com.spring.core.app.v4;


import com.spring.core.logTrace.LogTrace;
import com.spring.core.trace.TraceStatus;
import com.spring.core.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId){

        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            public Void call() {
                if(itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생!");
                }
                //상품을 저장하는데 1초 정도 걸린다고 가정
                sleep(1000);
                return null;
            }
        };

        template.execute("OrderRepository.save()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
