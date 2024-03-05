package com.spring.core.trace;

import com.spring.core.trace.TraceId;
import com.spring.core.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class HelloTraceV2 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    public TraceStatus begin(String message){
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}",traceId.getId(),addSpace(START_PREFIX,traceId.getLevel()),message);

        //1.로그 출력
        //begin에서 TraceId를 담은 TraceStatus가 생성된 다음
        //끝날대 하나의 트랜잭션의 결과에 따라
        // end메서드 혹은 exception 메서드로 반환된다.
        return new TraceStatus(traceId, startTimeMs, message);

    }

    //V2추가
    public TraceStatus beginSync(TraceId beforeTraceId,String message){
        TraceId traceId = beforeTraceId.createNextId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}",traceId.getId(),addSpace(START_PREFIX,traceId.getLevel()),message);

        return new TraceStatus(traceId, startTimeMs, message);
    }

    public TraceStatus record(String message,TraceId traceId){
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}",traceId.getId(),addSpace(START_PREFIX,traceId.getLevel()),message);

        //1.로그 출력
        //begin에서 TraceId를 담은 TraceStatus가 생성된 다음
        //끝날대 하나의 트랜잭션의 결과에 따라
        // end메서드 혹은 exception 메서드로 반환된다.
        return new TraceStatus(traceId, startTimeMs, message);
    }

    public void end(TraceStatus status){
        complete(status,null);
    }

    public  void exception(TraceStatus status, Exception e){
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e){
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();

        if(e == null){
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX,traceId.getLevel()),status.getMessage(),resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()),status.getMessage(),resultTimeMs,e.toString());
        }

    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for(int i =0 ; i < level; i++){
            sb.append((i == level - 1) ? "|" + prefix : "|  ");
        }
        return sb.toString();
    }
}
