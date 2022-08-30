package solution;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.RoundingMode.UNNECESSARY;

public class LongMain {

  private static final BigDecimal TWO = BigDecimal.valueOf(2);
  public static final double MAGIC_FACTOR = 5.8284271247;

  public static void main(String[] args) {
    System.out.println("(6, 8)");
    var count = 1;
    var i = 35L;
    var j = calculateSecondValue(i);
    while (count < 15) {
      if (validate(i, j)) {
        System.out.println("(" + i + ", " + j + ")");
        count++;
        i = (long) (i * MAGIC_FACTOR);
      }
      i++;
      j = calculateSecondValue(i);
    }
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
    return sum(first.subtract(ONE));
  }

  private static BigDecimal sum(BigDecimal n) {
    return n.multiply(n.add(ONE)).divide(TWO, UNNECESSARY);
  }

}