package park.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by park on 2022/03/27.
 */
@Slf4j
public class SubClassLogic2 extends AbstractTemplate {

  @Override
  protected void call() {
    log.info("비지니스 로직2 실행");
  }
}
