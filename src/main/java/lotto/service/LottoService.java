package lotto.service;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.domain.Rank;

import java.util.*;
import java.util.stream.Collectors;

public class LottoService {
    private Lottos lottos;
    private final Map<Rank, Integer> resultMap = new EnumMap<>(Rank.class);

    public LottoService() {
        for (Rank rank : Rank.values()) {
            resultMap.put(rank, 0);
        }
    }

    public Lottos createLottos(int count) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoList.add(new Lotto(generateRandomNumbers()));
        }
        lottos = new Lottos(lottoList);
        return lottos;
    }

    public void createFixedLottos(List<List<Integer>> fixedNumbers) {
        List<Lotto> lottoList = fixedNumbers.stream()
                .map(Lotto::new)
                .collect(Collectors.toList());
        lottos = new Lottos(lottoList);
    }

    private List<Integer> generateRandomNumbers() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 45; i++) nums.add(i);
        Collections.shuffle(nums);
        return nums.subList(0, 6).stream().sorted().toList();
    }

    public void compareLotto(List<Integer> winNums, int bonusNum) {
        for (Rank r : Rank.values()) resultMap.put(r, 0);

        for (Lotto lotto : lottos.getLottoList()) {
            int matchCount = (int) lotto.getNumbers().stream()
                    .filter(winNums::contains)
                    .count();
            boolean bonusMatch = lotto.getNumbers().contains(bonusNum);
            Rank rank = Rank.valueOf(matchCount, bonusMatch);
            resultMap.put(rank, resultMap.get(rank) + 1);
        }
    }

    public double calculateRate(int totalMoney) {
        int profit = resultMap.entrySet().stream()
                .mapToInt(e -> e.getKey().getPrize() * e.getValue())
                .sum();
        return ((double) profit / totalMoney) * 100;
    }

    public Map<Rank, Integer> getResultMap() {
        return resultMap;
    }

    public Lottos getLottos() {
        return lottos;
    }

    public List<Integer> RandLottoNums() {
        return generateRandomNumbers();
    }
}
