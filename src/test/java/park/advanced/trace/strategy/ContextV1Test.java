package park.advanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import park.advanced.trace.strategy.code.strategy.ContextV1;
import park.advanced.trace.strategy.code.strategy.Strategy;
import park.advanced.trace.strategy.code.strategy.StrategyLogic1;
import park.advanced.trace.strategy.code.strategy.StrategyLogic2;

/**
 * Created by park on 2022/03/27.
 */
@Slf4j
public class ContextV1Test {
  @Test
  void templateMethodV0() {
    logic1();
    logic2();
  }

  private void logic1() {
    long startTime = System.currentTimeMillis();
    //비지니스 로직 실행
    log.info("비지니스 로직1 실행");
    //비지니스 로직 종료
    long endTime = System.currentTimeMillis();

    long resultTime = endTime - startTime;
    log.info("resultTime={}",resultTime);

  }

  private void logic2() {
    long startTime = System.currentTimeMillis();
    //비지니스 로직 실행
    log.info("비지니스 로직2 실행");
    //비지니스 로직 종료
    long endTime = System.currentTimeMillis();

    long resultTime = endTime - startTime;
    log.info("resultTime={}",resultTime);

  }

  @Test
  void strategyV1(){
    StrategyLogic1 logic1 = new StrategyLogic1();
    ContextV1 v1 = new ContextV1(logic1);
    v1.execute();

    StrategyLogic2 logic2 = new StrategyLogic2();
    ContextV1 v2 = new ContextV1(logic2);
    v2.execute();
  }
  @Test
  void strategyV2(){
    Strategy logic1 = new Strategy() {
      @Override
      public void call() {
        log.info("비지니스 로직2 실행");
      }
    };
    ContextV1 v1 = new ContextV1(logic1);
    v1.execute();

    Strategy logic2 = new Strategy() {
      @Override
      public void call() {
        log.info("비지니스 로직2 실행");
      }
    };
    ContextV1 v2 = new ContextV1(logic2);
    v2.execute();
  }
}
