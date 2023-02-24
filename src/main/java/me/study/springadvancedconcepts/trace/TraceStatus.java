package me.study.springadvancedconcepts.trace;

public class TraceStatus {
    private TraceId traceId;
    private Long startedTime; // ms
    private String message;

    public TraceStatus(TraceId traceId, Long startedTime, String message) {
        this.traceId = traceId;
        this.startedTime = startedTime;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartedTime() {
        return startedTime;
    }

    public String getMessage() {
        return message;
    }
}
