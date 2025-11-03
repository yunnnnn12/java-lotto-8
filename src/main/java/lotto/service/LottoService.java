// src/main/java/lotto/service/LottoService.java
package lotto.service;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.domain.Rank;

import java.util.*;
import java.util.stream.Collectors;

public class LottoService {
    private Lottos lottos;
    private final Map<Rank, Integer> resultMap = new EnumMap<>(Rank.class);

    // 테스트용 랜덤 번호 큐
    private Queue<List<Integer>> testRandomQueue = new LinkedList<>();

    public LottoService() {
        for (Rank rank : Rank.values()) {
            resultMap.put(rank, 0);
        }
    }

    // 테스트용 랜덤 번호 설정
    public void setTestRandomNumbers(List<List<Integer>> fixedRandomNumbers) {
        testRandomQueue.clear();
        testRandomQueue.addAll(fixedRandomNumbers);
    }

    // 랜덤 로또 생성
    public Lottos createLottos(int count) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoList.add(new Lotto(generateRandomNumbers()));
        }
        lottos = new Lottos(lottoList);
        return lottos;
    }

    // 테스트용/고정 번호 로또 생성
    public void createFixedLottos(List<List<Integer>> fixedNumbers) {
        List<Lotto> lottoList = fixedNumbers.stream()
                .map(Lotto::new)
                .collect(Collectors.toList());
        lottos = new Lottos(lottoList);
    }

    // 랜덤 번호 생성 (테스트용 큐가 있으면 먼저 사용)
    private List<Integer> generateRandomNumbers() {
        if (!testRandomQueue.isEmpty()) {
            return testRandomQueue.poll();
        }
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 45; i++) nums.add(i);
        Collections.shuffle(nums);
        return nums.subList(0, 6).stream().sorted().toList();
    }

    // 테스트용 public 메서드 (ApplicationTest에서 필요)
    public List<Integer> RandLottoNums() {
        return generateRandomNumbers();
    }

    // 당첨 결과 계산
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

    // 수익률 계산
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
}
