package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.contants.InputViewMessage;

public class InputView {
    public String getVisitDate() {
        System.out.println(InputViewMessage.VISIT_DATE_INPUT.getMessage());
        return Console.readLine();
    }

    public String getMenuAndCount() {
        System.out.println(InputViewMessage.MENU_AND_COUNT_INPUT.getMessage());
        return Console.readLine();
    }
}
