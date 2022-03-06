package park.advanced.app.v2;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import park.advanced.trace.TraceStatus;
import park.advanced.trace.hellotrace.HelloTraceV1;
import park.advanced.trace.hellotrace.HelloTraceV2;

/**
 * Created by park on 2022/03/06.
 */
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

  private final HelloTraceV2 trace;

  public void save(TraceStatus status,String itemId) {

    try {
      status = trace.beginSync(status.getTraceId(),"OrderRepositoryV2.save()");
      //저장 로직
      if(itemId.equals("ex")) {
        throw new IllegalStateException("예외 발생");
      }
      sleep(1000 );

      trace.end(status);
    } catch (Exception e) {
      trace.exception(status,e);
      throw e;
    }


  }
  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    }catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
