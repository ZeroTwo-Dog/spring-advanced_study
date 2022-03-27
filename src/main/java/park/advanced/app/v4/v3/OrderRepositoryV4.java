package park.advanced.app.v4.v3;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import park.advanced.app.template.AbstractTemplate;
import park.advanced.trace.TraceStatus;
import park.advanced.trace.logtrace.LogTrace;

/**
 * Created by park on 2022/03/06.
 */
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {


  private final LogTrace trace;

  public void save(String itemId) {
    AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {


      @Override
      protected Void call() {
        if(itemId.equals("ex")) {
          throw new IllegalStateException("예외 발생");
        }
        sleep(1000 );        return null;
      }
    };
    template.execute("OrderRepository.save()");


  }
  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    }catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
