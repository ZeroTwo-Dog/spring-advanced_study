package park.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import park.advanced.trace.TraceStatus;
import park.advanced.trace.hellotrace.HelloTraceV1;

/**
 * Created by park on 2022/03/06.
 */
@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

  private final OrderServiceV1 orderServiceV1;
  private final HelloTraceV1 trace;

  @GetMapping("/v1/request")
  public String request(String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderControllerV1.request()");
      orderServiceV1.orderItem(itemId);
      trace.end(status);
      return "ok";
    } catch (Exception e) {
      trace.exception(status,e);
      throw e;
    }
  }
}
