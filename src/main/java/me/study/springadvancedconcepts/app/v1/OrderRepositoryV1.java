package me.study.springadvancedconcepts.app.v1;

import lombok.RequiredArgsConstructor;
import me.study.springadvancedconcepts.trace.TraceStatus;
import me.study.springadvancedconcepts.trace.app.TraceV1;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {
    private final TraceV1 trace;

    public void save(String itemId) {
        TraceStatus status = null;
        try{
            status = trace.begin("OrderRepository.save()");

            // Saved Logic
            if (itemId.equals("ex")) {
                throw new IllegalStateException("Exception 발생");
            }

            sleep(1000);

            trace.end(status);
        }catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 던저줘야 한다.
        }
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
