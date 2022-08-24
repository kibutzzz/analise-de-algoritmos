package solution;

import java.math.BigDecimal;

public class FormulaValidator implements Validator {
  @Override
  public boolean validate(BigDecimal first, BigDecimal second) {
    final var sum1 = sum(first.subtract(BigDecimal.ONE));
    final var sum2 = sum(second).subtract(sum(first));

    final var isValid = sum1.compareTo(sum2) == 0;
    if (!isValid) {
      System.out.printf("{%s -> %s \n %s -> %s}\n", first, sum1, second, sum2);
      return false;
    }

    return isValid;
  }

  private BigDecimal sum(BigDecimal n) {

    if (n.compareTo(BigDecimal.ZERO) <= 0) return BigDecimal.ZERO;
    return n.multiply(n.add(BigDecimal.ONE))
        .divide(BigDecimal.valueOf(2));
  }
}
