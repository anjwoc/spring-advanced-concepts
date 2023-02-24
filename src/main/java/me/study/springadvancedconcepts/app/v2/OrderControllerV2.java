package me.study.springadvancedconcepts.app.v2;

import lombok.RequiredArgsConstructor;
import me.study.springadvancedconcepts.trace.TraceStatus;
import me.study.springadvancedconcepts.trace.app.TraceV1;
import me.study.springadvancedconcepts.trace.app.TraceV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;
    private final TraceV2 trace;

    @GetMapping("/v2/request")
    public String request(@RequestParam String itemId) {
        TraceStatus status = null;
        try{
            status = trace.begin("OrderController.request()");
            orderService.orderItem(status.getTraceId(), itemId);
            trace.end(status);
            return "ok";
        }catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 던저줘야 한다.
        }
    }
}
