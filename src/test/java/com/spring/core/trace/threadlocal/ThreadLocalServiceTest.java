package com.spring.core.trace.threadlocal;


import com.spring.core.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();

    @Test
    public void threadLocal(){
        log.info("main start");


//        Runnable userEx = new Runnable() {
//            @Override
//            public void run() {
//                fieldService.logic("userA");
//            }
//        };

        // 보통 스레드는 2개가 경합을 할때 문제가 생긴다.
        // 그래서 멀티 스레드 환경을 구현한다.
        Runnable userA = () -> {
            service.logic("userA");
        };

        Runnable userB = () -> {
            service.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        //sleep(2000); //동시성 문제 발생X
        sleep(100);  //동시성 문제 발생O
        threadB.start();

        //메인쓰레드가 종료되어서 threadB가 정상적으로 실행되지 않고 종료된다.
        //메인 쓰레드 종료 대기
        sleep(3000);
        log.info("main exit");
    }

    private void sleep(int mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
