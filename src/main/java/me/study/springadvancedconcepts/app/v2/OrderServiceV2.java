package me.study.springadvancedconcepts.app.v2;

import lombok.RequiredArgsConstructor;
import me.study.springadvancedconcepts.trace.TraceId;
import me.study.springadvancedconcepts.trace.TraceStatus;
import me.study.springadvancedconcepts.trace.app.TraceV2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;
    private final TraceV2 trace;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId, "OrderService.orderItem()");
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);
        }catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 던저줘야 한다.
        }
    }

}
