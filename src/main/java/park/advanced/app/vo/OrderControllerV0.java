package park.advanced.app.vo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by park on 2022/03/06.
 */
@RestController
@RequiredArgsConstructor
public class OrderControllerV0 {

  private final OrderServiceV0 orderServiceV0;

  @GetMapping("/v0/request")
  public String request(String itemId) {
    orderServiceV0.orderItem(itemId);
    return "ok";
  }
}
