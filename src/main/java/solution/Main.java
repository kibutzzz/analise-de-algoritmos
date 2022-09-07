package solution;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.RoundingMode.UNNECESSARY;

public class Main {
  private static final BigDecimal TWO = BigDecimal.valueOf(2);

  public static void main(String[] args) {
    int i = 6;
    int j = 8;
    int total = 0;
    int lastValidI = i;

    while (total < 15) {
      if (validate(i, j)) {
        System.out.printf("(%d, %d)%n", i, j);
        total++;
        lastValidI = i;
      }
      i++;
      if (i == j) {
        j++;
        i = lastValidI;
      }

    }

  }

  private static boolean validate(int first, int second) {
    final var firstDecimal = BigDecimal.valueOf(first);
    final var sum1 = sumFirstDecimal(firstDecimal);
    final var sum2 = sumSecondDecimal(firstDecimal, BigDecimal.valueOf(second));

    return sum1.compareTo(sum2) == 0;
  }

  private static BigDecimal sumSecondDecimal(BigDecimal first, BigDecimal second) {
    return sum(second).subtract(sum(first));
  }

  private static BigDecimal sumFirstDecimal(BigDecimal first) {
    return sum(first.subtract(ONE));
  }

  private static BigDecimal sum(BigDecimal n) {
    return n.multiply(n.add(ONE)).divide(TWO, UNNECESSARY);
  }

}



