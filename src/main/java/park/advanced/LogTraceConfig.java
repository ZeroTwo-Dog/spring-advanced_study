package park.advanced;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import park.advanced.trace.logtrace.FieldLogTrace;
import park.advanced.trace.logtrace.LogTrace;

/**
 * Created by park on 2022/03/13.
 */
@Configuration
public class LogTraceConfig {

  @Bean
  public LogTrace logTrace() {
    return new FieldLogTrace();
  }
}
