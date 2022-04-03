package park.advanced.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import park.advanced.trace.strategy.code.strategy.Strategy;

/**
 * Created by park on 2022/04/03.
 */
@Slf4j
public class TimeLogTemplate {

  public void execute (Callback callback) {
    long startTime = System.currentTimeMillis();
    //비지니스 로직 실행
    callback.call(); //위임
    //비지니스 로직 종료
    long endTime = System.currentTimeMillis();

    long resultTime = endTime - startTime;
    log.info("resultTime={}",resultTime);
  }


}
