package park.advanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import park.advanced.trace.strategy.code.strategy.ContextV1;
import park.advanced.trace.strategy.code.strategy.ContextV2;
import park.advanced.trace.strategy.code.strategy.Strategy;
import park.advanced.trace.strategy.code.strategy.StrategyLogic1;
import park.advanced.trace.strategy.code.strategy.StrategyLogic2;

/**
 * Created by park on 2022/03/27.
 */
@Slf4j
public class ContextV2Test {

  @Test
  void strategyV1(){
    ContextV2 context = new ContextV2();
    context.execute(new StrategyLogic1());
    context.execute(new StrategyLogic2());
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
