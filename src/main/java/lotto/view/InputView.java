package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class InputView {
    public int returnTotalMoney() {
        while (true) {
            String input = Console.readLine().trim();
            try {
                int totalMoney = Integer.parseInt(input);
                if (totalMoney % 1000 != 0) {
                    System.out.println("[ERROR] 로또 구입 금액은 1000원 단위여야 합니다: " + totalMoney);
                    continue;
                }
                return totalMoney;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자만 입력해야 합니다: " + input);
            }
        }
    }

    public List<Integer> returnWinNumList() {
        String input = Console.readLine().trim();
        String[] nums = input.split(",");
        if (nums.length != 6) {
            System.out.println("[ERROR] 번호는 쉼표로 구분하여 6개 입력해야 합니다.");
            throw new IllegalArgumentException("[ERROR] 번호는 쉼표로 구분하여 6개 입력해야 합니다.");
        }
        List<Integer> winNumList = new ArrayList<>();
        for (String num : nums) {
            int n;
            try {
                n = Integer.parseInt(num.trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자만 입력해야 합니다: " + num);
                throw new IllegalArgumentException("[ERROR] 숫자만 입력해야 합니다: " + num);
            }
            if (winNumList.contains(n)) {
                System.out.println("[ERROR] 중복된 번호가 있습니다: " + n);
                throw new IllegalArgumentException("[ERROR] 중복된 번호가 있습니다: " + n);
            }
            if (n < 1 || n > 45) {
                System.out.println("[ERROR] 로또 번호는 1~45 사이여야 합니다: " + n);
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 사이여야 합니다: " + n);
            }
            winNumList.add(n);
        }
        return winNumList;
    }

    public int returnBonusNum(List<Integer> winNumList) {
        String input = Console.readLine().trim();
        int bonus;
        try {
            bonus = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 보너스 번호는 숫자여야 합니다: " + input);
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다: " + input);
        }
        if (winNumList.contains(bonus)) {
            System.out.println("[ERROR] 보너스 번호는 당첨 번호와 겹치면 안 됩니다: " + bonus);
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 겹치면 안 됩니다: " + bonus);
        }
        if (bonus < 1 || bonus > 45) {
            System.out.println("[ERROR] 보너스 번호는 1~45 사이여야 합니다: " + bonus);
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 사이여야 합니다: " + bonus);
        }
        return bonus;
    }
}
