package com.spring.core.trace.strategy;

import com.spring.core.trace.strategy.code.strategy.ContextV2;
import com.spring.core.trace.strategy.code.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {
    @Test
    void strategyV1(){
        ContextV2 context = new ContextV2();
        context.execute(()-> log.info("비지니스 로직1 실행"));
        context.execute(()-> log.info("비지니스 로직2 실행"));
    }

    @Test
    void strategyV2(){
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비지니스 로직1 실행"));
        context.execute(() -> log.info("비지니스 로직2 실행"));
    }
}
