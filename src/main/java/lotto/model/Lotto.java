package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
        this.numbers.sort(Integer::compareTo);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        if (numbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }

        for (int n : numbers) {
            if (n < 1 || n > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 사이여야 합니다: " + n);
            }
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
