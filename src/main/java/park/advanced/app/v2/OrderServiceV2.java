package park.advanced.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import park.advanced.trace.TraceStatus;
import park.advanced.trace.hellotrace.HelloTraceV1;
import park.advanced.trace.hellotrace.HelloTraceV2;

/**
 * Created by park on 2022/03/06.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

  private final OrderRepositoryV2 orderRepository;
  private final HelloTraceV2 trace;


  public void orderItem (TraceStatus status,String itemId) {
    try {
      status = trace.beginSync(status.getTraceId(),"OrderServiceV2.orderItem()");
      orderRepository.save(status,itemId);
      trace.end(status);
    } catch (Exception e) {
      trace.exception(status,e);
      throw e;
    }
  }
}
