package park.advanced.app.v5;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import park.advanced.app.template.AbstractTemplate;
import park.advanced.trace.callback.TraceCallback;
import park.advanced.trace.callback.TraceTemplate;
import park.advanced.trace.logtrace.LogTrace;

/**
 * Created by park on 2022/03/06.
 */
@Repository
public class OrderRepositoryV5 {


  private final TraceTemplate traceTemplate;

  public OrderRepositoryV5(LogTrace logTrace) {
    this.traceTemplate = new TraceTemplate(logTrace);
  }

  public void save(String itemId) {
    traceTemplate.execute("OrderRepository.save()", new TraceCallback<Object>() {
      @Override
      public Void callback() {
        if(itemId.equals("ex")) {
          throw new IllegalStateException("예외 발생");
        }
        sleep(1000 );        return null;      }
    });


  }
  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    }catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
