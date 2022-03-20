package park.advanced.trace.threadLocal.code;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by park on 2022/03/20.
 */
@Slf4j
public class ThreadLocalService {


  private ThreadLocal<String> nameStroe =  new ThreadLocal<>();

  public String logic(String name) {
    log.info("name 저장={} -> nameStore={} ",name,nameStroe.get());
    nameStroe.set(name);
    sleep(1000);

    log.info("조회 nameStroe={}",nameStroe.get());
    return nameStroe.get();
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
