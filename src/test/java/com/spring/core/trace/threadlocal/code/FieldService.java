package com.spring.core.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class FieldService {

    private String nameStore;

    public String logic(String name){
        // 1. 파라미터 name값, nameStore 값 출력
        log.info("저장 name={} -> nameStore={}",name, nameStore);

        // 2.파라미터로 들어온 name nameStore 필드에 저장
        nameStore = name;
        sleep(1000);

        // 3. nameStore값 조회
        log.info("조회 nameStore={}", nameStore);
        return nameStore;
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
