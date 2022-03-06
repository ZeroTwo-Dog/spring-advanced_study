package park.advanced.trace;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by park on 2022/03/06.
 */
@Getter
@AllArgsConstructor
public class TraceStatus {

  private TraceId traceId;
  private Long startTimeMs;
  private String message;



}
