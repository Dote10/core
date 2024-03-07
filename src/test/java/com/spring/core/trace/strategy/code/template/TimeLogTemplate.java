package com.spring.core.trace.strategy.code.template;

import com.spring.core.trace.callback.TraceCallback;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

    public void execute(TraceCallback callback){
        long startTime = System.currentTimeMillis();
        //비지니스 로직 실행
        callback.call(); //위임
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}",resultTime);
    }
}
