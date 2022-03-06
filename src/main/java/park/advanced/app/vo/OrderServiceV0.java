package park.advanced.app.vo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by park on 2022/03/06.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceV0 {

  private final OrderRepositoryV0 orderRepository;


  public void orderItem (String itemId) {
    orderRepository.save(itemId);
  }
}
