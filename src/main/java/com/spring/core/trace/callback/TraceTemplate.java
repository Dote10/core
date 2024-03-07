package com.spring.core.trace.callback;

import com.spring.core.logTrace.LogTrace;
import com.spring.core.trace.TraceStatus;
import org.springframework.stereotype.Component;


public class TraceTemplate {
    LogTrace trace;

    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public <T> T  execute(String message, TraceCallback<T> callback){
        TraceStatus status = null;
        try {
            status = trace.begin(message);
            //로직 호출
            T result = callback.call();
            trace.end(status);
            return  result;
        }catch (Exception e){
            trace.exception(status,e);
            throw e;
        }

    }
}
