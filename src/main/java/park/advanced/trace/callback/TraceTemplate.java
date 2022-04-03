package park.advanced.trace.callback;

import park.advanced.trace.TraceStatus;
import park.advanced.trace.logtrace.LogTrace;

/**
 * Created by park on 2022/04/03.
 */
public class TraceTemplate {

  private final LogTrace trace;

  public TraceTemplate(LogTrace trace) {
    this.trace = trace;
  }

  public <T> T execute(String message, TraceCallback<T> traceCallback) {
    TraceStatus status = null;
    try {
      status = trace.begin(message); //로직 호출

      // 템플릿 콜북 사용부분  위 엑스큐트 실행할대 아래 콜백 구현
      T result = traceCallback.callback();
      trace.end(status);
      return result;
    } catch (Exception e) {
      trace.exception(status, e);
      throw e; }

  }
}
