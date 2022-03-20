package park.advanced.trace;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by park on 2022/03/20.
 */
public class StreamTest {

  public class A {
    private Long id;

    private String name;

    public A(Long id, String name) {
      this.id =id;
      this.name = name;
    }
    
    public A () {}

    public A(List<A> list) {
      this.id = list.get(0).id;
      this.name = list.get(0).name;
    }


    public A(B b) {
      this.id =(long) b.id;
      this.name = b.name;
    }


    public String getName() {
      return name;
    }

    public Long getId() {
      return id;
    }

    @Override
    public int hashCode() {
      return Objects.hash(id,name);
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof A)) {
        return false;
      }
      A a = (A) obj;
      return a.getId().equals(this.getId()) && a.getName().equals(this.getName());
    }
  }


  public class B {
    private int id;
    private String name;
    private String value;

    public B(int id, String name, String value) {
      this.id =id;
      this.name = name;
      this.value = value;
    }


    public String getValue() {
      return value;
    }

    public B () {}

  }

  @Test
  public void streamGroupingByTest() throws Exception {
    //given
    List<A> aList= new ArrayList<>();
    aList.add(new A(2L, "test2"));
    aList.add(new A(5L, "test5"));
    aList.add(new A(1L, "test1"));
    aList.add(new A(1L, "test11"));
    aList.add(new A(4L, "test4"));
    //when
    Map<Long, List<A>> collect = aList.stream().collect(groupingBy(a -> a.id));
    List<A> collect1 = aList.stream().collect(groupingBy(a -> a.id)).values().stream()
        .map(A::new).collect(toList());
    System.out.println();

    Assertions.assertThat(collect.get(1L).get(0)).isEqualTo(aList.get(2));
  }

  @Test
  public void streamGroupingByMappingTest() throws Exception {
    //given
    List<B> bList= new ArrayList<>();
    bList.add(new B(3, "test3","value3"));
    bList.add(new B(2, "test2","value2"));
    bList.add(new B(1, "test1","value1"));
    bList.add(new B(1, "test1","value11"));
    bList.add(new B(5, "test5","value5"));
    Comparator<B> bComparator = new Comparator<B>() {
      @Override
      public int compare(B o1, B o2) {
        return o1.id - o2.id;
      }
    };
//    Collections.sort(bList, bComparator); // id 오름차순
    //when
    Map<A, List<String>> collect = bList.stream()
        .collect(groupingBy(b -> new A((long) b.id, b.name),
            mapping(B::getValue, toList()))
        );

//    collect.forEach((a, strings) -> System.out.println(strings));
    //then
    Assertions.assertThat(collect.values().stream().findFirst().get().get(0)).isEqualTo("value1");
  }

}
