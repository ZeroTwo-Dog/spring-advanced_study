package park.advanced.trace.hellotrace;


import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import park.advanced.trace.TraceId;
import park.advanced.trace.TraceStatus;

class HelloTraceV2Test {

  @Test
  public void begin_end() throws Exception {
    HelloTraceV2 trace = new HelloTraceV2();
    TraceStatus status1 = trace.begin("start");
    TraceStatus status2 = trace.beginSync(status1.getTraceId(), "start2");


    trace.end(status1);
    trace.end(status2);

    assertThat(status1.getTraceId().getId()).isEqualTo(status2.getTraceId().getId());
  }

  @Test
  public void begin_exception() throws Exception {
    HelloTraceV2 trace = new HelloTraceV2();
    TraceStatus status1 = trace.begin("start");
    TraceStatus status2 = trace.beginSync(status1.getTraceId(), "start2");


    trace.exception(status1, new IllegalStateException());
    trace.exception(status2, new IllegalStateException());

    assertThat(status1.getTraceId().getId()).isEqualTo(status2.getTraceId().getId());

  }

}