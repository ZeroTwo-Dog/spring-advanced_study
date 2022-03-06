package park.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import park.advanced.trace.TraceStatus;
import park.advanced.trace.hellotrace.HelloTraceV1;

/**
 * Created by park on 2022/03/06.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

  private final OrderRepositoryV1 orderRepository;
  private final HelloTraceV1 trace;


  public void orderItem (String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderServiceV1.orderItem()");
      orderRepository.save(itemId);
      trace.end(status);
    } catch (Exception e) {
      trace.exception(status,e);
      throw e;
    }
  }
}
