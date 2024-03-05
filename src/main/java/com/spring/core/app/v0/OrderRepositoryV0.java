package com.spring.core.app.v0;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {


    public void save(String itemId){
        if(itemId.equals("ex")){
            throw new IllegalStateException("예외 발생!");
        }

        //상품을 저장하는데 1초 정도 걸린다고 가정
        sleep(1000);

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
