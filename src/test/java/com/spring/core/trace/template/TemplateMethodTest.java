package com.spring.core.trace.template;

import com.spring.core.trace.template.code.AbstractTemplate;
import com.spring.core.trace.template.code.SubClassLoic1;
import com.spring.core.trace.template.code.SubClassLoic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0(){
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();
        //비지니스 로직 실행
        log.info("비지니스 로직1 실행");
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}",resultTime);
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();
        //비지니스 로직 실행
        log.info("비지니스 로직1 실행");
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}",resultTime);
    }

    /**
     * 템플릿 메서드 패턴 적용
     */
    @Test
    void templateMethodV1(){
        AbstractTemplate template1 = new SubClassLoic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLoic2();
        template2.execute();

    }

    /**
     * 템플릿 메서드 패턴, 익명 내부 클래스 사용
     */
    @Test
    void templateMethodV2(){
        //익명내부 클래스로
        // 객체를 생성하는 동시에 구현할 수 있다.
        //즉 추상 클래스를 상속 받은 클래스를 바로 만들 수 있다.
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                 log.info("비지니스 로직1 실행");
            }
        };
        log.info("클래스 이름1 ={}",template1.getClass());
        template1.execute();
        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비지니스 로직2 실행");
            }
        };
        log.info("클래스 이름2 ={}",template2.getClass());
        template2.execute();
    }
}
