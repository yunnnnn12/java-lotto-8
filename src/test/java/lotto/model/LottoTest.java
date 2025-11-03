package lotto;

import lotto.model.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개보다 많으면 예외가 발생한다")
    @Test
    void 로또_번호의_개수가_6개_넘으면_예외발생() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 개수가 6개보다 적으면 예외가 발생한다")
    @Test
    void 로또_번호의_개수가_6개_안되면_예외발생() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복이 있으면 예외가 발생한다")
    @Test
    void 로또_번호에_중복있으면_예외발생() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("올바른 로또 번호는 정상 생성된다")
    @Test
    void 올바른_로또_번호는_정상생성() {
        new Lotto(List.of(1, 2, 3, 4, 5, 6)); // 예외가 발생하지 않으면 테스트 통과
    }
}
