package park.advanced.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import park.advanced.trace.strategy.code.template.Callback;
import park.advanced.trace.strategy.code.template.TimeLogTemplate;

/**
 * Created by park on 2022/04/03.
 */
@Slf4j
public class TemplateCallbackTest {

  @Test
  public void callbackV1() throws Exception {
    TimeLogTemplate template = new TimeLogTemplate();

    template.execute(new Callback() {
      @Override
      public void call() {
        log.info("비지니스 로직 1");
      }
    });

    template.execute(new Callback() {
      @Override
      public void call() {
        log.info("비지니스 로직 2");
      }
    });
  }
  
  @Test
  public void callbackV2() throws Exception {
    //given
    TimeLogTemplate template = new TimeLogTemplate();
    template.execute(() -> log.info("비지니스 로직1"));
    //when
    
    //then
  }

}
