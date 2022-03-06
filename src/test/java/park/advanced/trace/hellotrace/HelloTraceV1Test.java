package park.advanced.trace.hellotrace;


import org.junit.jupiter.api.Test;
import park.advanced.trace.TraceStatus;

class HelloTraceV1Test {
  
  @Test
  public void begin_end() throws Exception {
    HelloTraceV1 trace = new HelloTraceV1();
    TraceStatus status = trace.begin("hello");

    trace.end(status);

  }

  @Test
  public void begin_exception() throws Exception {
    HelloTraceV1 trace = new HelloTraceV1();
    TraceStatus status = trace.begin("hello");

    trace.exception(status, new IllegalStateException());

  }

}