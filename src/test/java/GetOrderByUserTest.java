import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static steps.CheckSteps.*;
import static steps.OrderSteps.*;
import static steps.UserSteps.createNewUserAndRegisterHim;

public class GetOrderByUserTest extends BaseTest {

    @Test
    @DisplayName("Позитивный тест получения списка заказов авторизованным пользователем")
    public void getOrderByUserCorrectSuccess() {
        createNewUserAndRegisterHim();
        sendCommonOrderWithAuth();
        Response response = getOrderByUserWithAuth();
        checkIfResponseCodeOk(response);
        checkIfKeySuccessValueEqualsTrue(response);
    }

    @Test
    @DisplayName("Негативный тест получения списка заказов неавторизованным пользователем")
    public void getOrderByUserNoAuthFail() {
        createNewUserAndRegisterHim();
        sendCommonOrderWithAuth();
        Response response = getOrderByUserWithoutAuth();
        checkIfResponseCodeUnauthorized(response);
        checkIfKeySuccessValueEqualsFalse(response);
        checkIfKeyMessageValueEqualsYouShouldBeAuthorised(response);
    }
}
