package park.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드 전력 보관
 * Created by park on 2022/03/27.
 */
@Slf4j
public class ContextV2 {

    public void execute (Strategy strategy) {
      long startTime = System.currentTimeMillis();
      //비지니스 로직 실행
      strategy.call(); //위임
      //비지니스 로직 종료
      long endTime = System.currentTimeMillis();

      long resultTime = endTime - startTime;
      log.info("resultTime={}",resultTime);
    }

}
