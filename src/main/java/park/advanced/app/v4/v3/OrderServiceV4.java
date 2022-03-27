package park.advanced.app.v4.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import park.advanced.app.template.AbstractTemplate;
import park.advanced.trace.logtrace.LogTrace;

/**
 * Created by park on 2022/03/06.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

  private final OrderRepositoryV4 orderRepository;
  private final LogTrace trace;

  public void orderItem (String itemId) {
    AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {


      @Override
      protected Void call() {
        orderRepository.save(itemId);
        return null;
      }
    };
    template.execute("OrderService.orderItem()");
  }
}
