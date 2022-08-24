package solution;


import java.math.BigDecimal;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LongMain {

  private static final List<Map.Entry<Long, Long>> list = new ArrayList<>();
  private static final BigDecimal TWO = BigDecimal.valueOf(2);


  public static void main(String[] args) {
    list.add(new SimpleEntry<>(6L,8L));
    var i = 35L;
    var j = calculateSecondValue(i);
    while (!shouldExit()) {

      if (validate(i, j)) {
        addToList(i, j);
        System.out.printf("%d - %d\n", i, j);
        i = (long) (i * 5.8);
      }
      i++;
      j = calculateSecondValue(i);

    }

    print();
  }

  private static void addToList(Long i, Long j) {
    list.add(new SimpleEntry<>(i, j));
  }

  private static boolean shouldExit() {
    return list.size() >= 15;
  }

  private static long calculateSecondValue(long firstValue) {
    return (long) ((-1 + Math.sqrt(1 + Math.pow(firstValue, 2) * 8)) / 2);
  }

  private static boolean validate(long first, long second) {
    final var firstDecimal = BigDecimal.valueOf(first);
    final var sum1 = sumFirstDecimal(firstDecimal);
    final var sum2 = sumSecondDecimal(firstDecimal, BigDecimal.valueOf(second));

    return sum1.compareTo(sum2) == 0;
  }

  private static BigDecimal sumSecondDecimal(BigDecimal first, BigDecimal second) {
    return sum(second).subtract(sum(first));
  }

  private static BigDecimal sumFirstDecimal(BigDecimal first) {
    return sum(first.subtract(BigDecimal.ONE));
  }

  private static BigDecimal sum(BigDecimal n) {
    if (n.compareTo(BigDecimal.ZERO) < 0) return BigDecimal.ZERO;
    return n.multiply(n.add(BigDecimal.ONE)).divide(TWO);
  }


  private static void print() {
    for (int i = 0; i < list.size(); i++) {
      final var item = list.get(i);
      System.out.println((i + 1) + "(" + item.getKey() + ", " + item.getValue() + ") ");
    }
  }

}



