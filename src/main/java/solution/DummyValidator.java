package solution;

public class DummyValidator  {

  public boolean validate(long a, long b) {

    var firstSum = 0;
    for(int i = 1; i < a; i++) {
      firstSum +=i;
    }

    var secondSum = 0;
    for(long i = a + 1; i <= b; i++) {
      secondSum += i;
    }

    return firstSum == secondSum;
  }
}
