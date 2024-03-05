package com.spring.core.logTrace;

import com.spring.core.trace.TraceId;
import com.spring.core.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
public class FieldLogTrace implements LogTrace{

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    //traceId를 어딘가에는 저장하고 있어야 한다.
    //traceId를 파라미터로 넘기는 것이 아니라 holder에 보관
    private TraceId traceIdHolder; // traceId 동기화, 동시성 이슈 발생

    /**
     * traceId를 동기화하는 메서드를 만든다.
     */
    private void syncTraceId(){
        if(traceIdHolder == null){
            //traceIdHolder가 null 일때는 새로 생성
            traceIdHolder = new TraceId();
        } else {
            //null이 아닐 경우 level에 + 1 해준다.
            traceIdHolder = traceIdHolder.createNextId();
        }
    }


    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        //동기화를 하고 나면
        //traceIdHolder에 값이 들어가 있는 것이 보장된다.
        TraceId traceId = traceIdHolder;
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}",traceId.getId(),addSpace(START_PREFIX,traceId.getLevel()),message);

        return new TraceStatus(traceId, startTimeMs, message);
    }

    @Override
    public void end(TraceStatus status) {

        complete(status,null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status,e);
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

        //complete메서드를 불러 올 때는
        //traceIdHolder에 level의 값을 -1 해줄것이다.
        releaseTraceId();

    }

    private void releaseTraceId() {
        if(traceIdHolder.isFirstLevel()){
            //end나 exception에서 부른
            //traceIdHolder의 level이 0이라는것은
            //해당 트랜잭션이 끝나기 전에 호출되었 다는 것이다.
            traceIdHolder = null; //destory
        }else{
            //해당 트랜잭션이 끝나기 직전에 호출된 것이 아니라면
            //traceIdHolder의 level에 -1을 해준다.
            traceIdHolder.createPreviousId();
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
