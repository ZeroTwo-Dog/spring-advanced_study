package park.advanced.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import park.advanced.app.template.AbstractTemplate;
import park.advanced.trace.callback.TraceCallback;
import park.advanced.trace.callback.TraceTemplate;
import park.advanced.trace.logtrace.LogTrace;

/**
 * Created by park on 2022/03/06.
 */
@Service
public class OrderServiceV5 {

  private final OrderRepositoryV5 orderRepository;
  private final TraceTemplate traceTemplate;

  public OrderServiceV5(OrderRepositoryV5 orderRepository,
      LogTrace logTrace) {
    this.orderRepository = orderRepository;
    this.traceTemplate = new TraceTemplate(logTrace);
  }

  public void orderItem (String itemId) {
    traceTemplate.execute("OrderService.orderItem()", new TraceCallback<>() {
      @Override
      public Void callback() {
        orderRepository.save(itemId);
        return null;
      }
    });

  }
}
