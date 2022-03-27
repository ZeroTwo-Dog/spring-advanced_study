package park.advanced.app.template;

import park.advanced.trace.TraceStatus;
import park.advanced.trace.logtrace.LogTrace;

/**
 * Created by park on 2022/03/27.
 */
public abstract class AbstractTemplate<T> {

  private LogTrace trace;

  public AbstractTemplate(LogTrace trace) {
    this.trace = trace;
  }


  public T execute(String message) {
    TraceStatus status = null;
    try {
      status = trace.begin(message);
      //로직 호출

      T result = call();

      trace.end(status);

      return result;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }


  protected abstract T call();
}
