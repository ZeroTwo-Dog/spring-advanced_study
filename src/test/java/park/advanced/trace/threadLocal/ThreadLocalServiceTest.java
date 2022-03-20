package park.advanced.trace.threadLocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import park.advanced.trace.threadLocal.code.FiledService;
import park.advanced.trace.threadLocal.code.ThreadLocalService;

/**
 * Created by park on 2022/03/20.
 */
@Slf4j
public class ThreadLocalServiceTest {

  private ThreadLocalService service = new ThreadLocalService();
  
  @Test
  public void filed() throws Exception {
    //given
    log.info("main start");
    //when
    Runnable userA = () -> {
      service.logic("userA");
    };
    Runnable userB = () -> {
      service.logic("userB");
    };
    //then
    Thread threadA = new Thread(userA);
    threadA.setName("thread-A");
    Thread threadB = new Thread(userB);
    threadB.setName("thread-B");

    threadA.start();
//    sleep(2000); // 동시성 문제 발생 X
    sleep(100); // 동시성 문제 발생 O
    threadB.start();
    sleep(3000); // 메인스레드 종료대기

    log.info("main exit");
  }

  private void sleep(int mills) {
    try {
      Thread.sleep(mills);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
