package park.advanced.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import park.advanced.app.template.AbstractTemplate;
import park.advanced.trace.callback.TraceCallback;
import park.advanced.trace.callback.TraceTemplate;
import park.advanced.trace.logtrace.LogTrace;

/**
 * Created by park on 2022/03/06.
 */
@RestController
public class OrderControllerV5 {

  private final OrderServiceV5 orderService;
  private final TraceTemplate traceTemplate;

  public OrderControllerV5(OrderServiceV5 orderService,
      LogTrace logTrace) {
    this.orderService = orderService;
    this.traceTemplate = new TraceTemplate(logTrace);
  }

  @GetMapping("/v5/request")
  public String request(String itemId) {
    return traceTemplate.execute("OrderController.request()", new TraceCallback<>() {
      @Override
      public String callback() {
        orderService.orderItem(itemId);
        return "ok";
      }
    });
  }
}
//
// LogTrace를 싱글톤으로 사용중이라서 traceholder를 여러개의 쓰레드가 접근해도 인스턴스가 1개이므로 id값도 동일하고 level도 충접되는 동시서 문제 발생
//2022-03-13 21:00:27.975  INFO 1298 --- [nio-8080-exec-6] p.advanced.trace.logtrace.FieldLogTrace  : [9743e281] OrderControllerV1.request()
//    2022-03-13 21:00:27.976  INFO 1298 --- [nio-8080-exec-6] p.advanced.trace.logtrace.FieldLogTrace  : [9743e281] |-->OrderServiceV2.orderItem()
//    2022-03-13 21:00:27.976  INFO 1298 --- [nio-8080-exec-6] p.advanced.trace.logtrace.FieldLogTrace  : [9743e281] |   |-->OrderRepositoryV2.save()
//    2022-03-13 21:00:28.118  INFO 1298 --- [nio-8080-exec-8] p.advanced.trace.logtrace.FieldLogTrace  : [9743e281] |   |   |-->OrderControllerV1.request()
//    2022-03-13 21:00:28.118  INFO 1298 --- [nio-8080-exec-8] p.advanced.trace.logtrace.FieldLogTrace  : [9743e281] |   |   |   |-->OrderServiceV2.orderItem()
//    2022-03-13 21:00:28.118  INFO 1298 --- [nio-8080-exec-8] p.advanced.trace.logtrace.FieldLogTrace  : [9743e281] |   |   |   |   |-->OrderRepositoryV2.save()
//    2022-03-13 21:00:28.981  INFO 1298 --- [nio-8080-exec-6] p.advanced.trace.logtrace.FieldLogTrace  : [9743e281] |   |<--OrderRepositoryV2.save() time=1005ms
//    2022-03-13 21:00:28.985  INFO 1298 --- [nio-8080-exec-6] p.advanced.trace.logtrace.FieldLogTrace  : [9743e281] |<--OrderServiceV2.orderItem() time=1009ms
//    2022-03-13 21:00:28.986  INFO 1298 --- [nio-8080-exec-6] p.advanced.trace.logtrace.FieldLogTrace  : [9743e281] OrderControllerV1.request() time=1011ms
//    2022-03-13 21:00:29.123  INFO 1298 --- [nio-8080-exec-8] p.advanced.trace.logtrace.FieldLogTrace  : [9743e281] |   |   |   |   |<--OrderRepositoryV2.save() time=1005ms
//    2022-03-13 21:00:29.124  INFO 1298 --- [nio-8080-exec-8] p.advanced.trace.logtrace.FieldLogTrace  : [9743e281] |   |   |   |<--OrderServiceV2.orderItem() time=1006ms
//    2022-03-13 21:00:29.125  INFO 1298 --- [nio-8080-exec-8] p.advanced.trace.logtrace.FieldLogTrace  : [9743e281] |   |   |<--OrderControllerV1.request() time=1007ms