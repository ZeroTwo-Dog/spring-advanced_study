package park.advanced.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import park.advanced.trace.TraceStatus;
import park.advanced.trace.hellotrace.HelloTraceV1;
import park.advanced.trace.hellotrace.HelloTraceV2;

/**
 * Created by park on 2022/03/06.
 */
@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

  private final OrderServiceV2 orderServiceV2;
  private final HelloTraceV2 trace;

  @GetMapping("/v2/request")
  public String request(String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderControllerV1.request()");
      orderServiceV2.orderItem(status,itemId);
      trace.end(status);
      return "ok";
    } catch (Exception e) {
      trace.exception(status,e);
      throw e;
    }
  }
}
