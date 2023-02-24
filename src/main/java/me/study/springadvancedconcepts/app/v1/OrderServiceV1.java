package me.study.springadvancedconcepts.app.v1;

import lombok.RequiredArgsConstructor;
import me.study.springadvancedconcepts.trace.TraceId;
import me.study.springadvancedconcepts.trace.TraceStatus;
import me.study.springadvancedconcepts.trace.app.TraceV1;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepository;
    private final TraceV1 trace;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try{
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        }catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 던저줘야 한다.
        }
    }

}
