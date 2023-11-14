package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;


public class BenefitReportTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    @DisplayName("증정 메뉴 이벤트에 해당할 경우 '샴페인 1개'가 출력된다.")
    void present_Event_Print() {
        assertSimpleTest(() -> {
            run("5", "티본스테이크-1,해산물파스타-1,레드와인-1");
            assertThat(output()).contains("<증정 메뉴>" + LINE_SEPARATOR + "샴페인 1개");
        });
    }

    @Test
    @DisplayName("증정 메뉴 이벤트에 해당하지 않을 경우 '없음'이 출력된다.")
    void present_Event_Nothing() {
        assertSimpleTest(() -> {
            run("5", "양송이수프-1,레드와인-1");
            assertThat(output()).contains("<증정 메뉴>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    @DisplayName("총 혜택 금액에 따라 이벤트 배지를 부여한다.")
    void event_Badge_By_Total_Benefit_Amount() {
        assertSimpleTest(() -> {
            run("5", "티본스테이크-2,초코케이크-1");
            assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "산타");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
