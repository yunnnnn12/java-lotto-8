package lotto.model;

import java.util.List;

public class Lottos {
    private final List<Lotto> lottoList;

    public Lottos() {
        this.lottoList = List.of();
    }

    public Lottos(List<Lotto> lottoList) {
        this.lottoList = lottoList;
    }

    public List<Lotto> getLottoList() {
        return lottoList;
    }
}
