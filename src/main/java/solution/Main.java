package solution;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.*;

public class Main {

  private static final List<Map.Entry<BigDecimal, BigDecimal>> list = new ArrayList<>();

  static {
    list.add(new SimpleEntry<>(valueOf(6), valueOf(8)));
    list.add(new SimpleEntry<>(valueOf(35), valueOf(49)));
  }

  public static final BigDecimal TWO = valueOf(2);
  public static final BigDecimal EIGHT = valueOf(8);

  public static void main(String[] args) {
    var i = calculateFirstValue();
    var j = calculateSecondValue(i);
    var retries = 0;
    while (!shouldExit()) {

      if (validate(i, j)) {
        addToList(i, j);
        i = calculateFirstValue();
        j = calculateSecondValue(i);
      } else {
        final var comparison = sumFirst(i).compareTo(sumSecond(i, j));
        if (comparison >0) {
          i = i.add(ONE);
        } else if(comparison <0){
          j = j.add(ONE);
        }
        if(retries++ > 10) break;
      }

    }

    print();
  }

  private static void print() {
    for (int i = 0; i < list.size(); i++) {
      final var item = list.get(i);
      System.out.println((i + 1) + "(" + item.getKey() + ", " + item.getValue() + ") ");
    }
  }

  private static void addToList(BigDecimal i, BigDecimal j) {
    list.add(new SimpleEntry<>(i, j));
  }

  private static BigDecimal calculateFirstValue() {
    final var lastNumber = list.get(list.size() - 1).getKey();
    final var penultimateNumber = list.get(list.size() - 2).getKey();
    final var factor = lastNumber.divide(penultimateNumber, MathContext.DECIMAL128);
    final var divide = lastNumber.multiply(factor).divide(ONE, 0, RoundingMode.HALF_DOWN);
    return divide;
  }

  private static boolean shouldExit() {
    return list.size() >= 15;
  }

  private static BigDecimal calculateSecondValue(BigDecimal firstValue) {
    return firstValue.pow(2)
        .multiply(EIGHT)
        .add(ONE)
        .sqrt(MathContext.DECIMAL32)
        .subtract(ONE)
        .divide(TWO, RoundingMode.FLOOR);
  }

  private static boolean validate(BigDecimal first, BigDecimal second) {
    final var sum1 = sumFirst(first);
    final var sum2 = sumSecond(first, second);

    final var isValid = sum1.compareTo(sum2) == 0;
    if (!isValid) {
      System.out.printf("{%s -> %s - %s -> %s}\n", first, sum1, second, sum2);
      return false;
    }

    return isValid;
  }

  private static BigDecimal sumSecond(BigDecimal first, BigDecimal second) {
    return sum(second).subtract(sum(first));
  }

  private static BigDecimal sumFirst(BigDecimal first) {
    return sum(first.subtract(BigDecimal.ONE));
  }

  private static BigDecimal sum(BigDecimal n) {

    if (n.compareTo(BigDecimal.ZERO) <= 0) return BigDecimal.ZERO;
    return n.multiply(n.add(BigDecimal.ONE))
        .divide(BigDecimal.valueOf(2));
  }


}



