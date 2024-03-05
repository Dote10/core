package com.spring.core.trace;

import java.util.UUID;

public class TraceId {

    private String id;
    
    //log 출력시 공백의 값
    private  int level;

    public TraceId(){
        this.id = createId();
        this.level = 0;
    }

    private  TraceId(String id, int level){
        this.id = id;
        this.level = level;
    }

    private String createId() {
        //앞 8자리만 사용
        return UUID.randomUUID().toString().substring(0,8);
    }

    //Controller -> Service 레이어 간의
    //depth 를 공백을 사용 직관적으로 표현 한다.
    public TraceId createNextId(){
        return  new TraceId(id,level + 1);
    }

    public TraceId createPreviousId(){
        return  new TraceId(id, level - 1);
    }

    public boolean isFirstLevel(){
        //first 레벨인지 아닌지
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
