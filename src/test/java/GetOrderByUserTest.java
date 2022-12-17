import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.Test;

import static helpers.AssertHelper.*;
import static helpers.OrderHelper.*;
import static helpers.UserHelper.createNewUserAndRegisterHim;

public class GetOrderByUserTest extends BaseTest {

    @Test
    @Description("Позитивный тест получения списка заказов авторизованным пользователем")
    public void getOrderByUserCorrectSuccess() {
        createNewUserAndRegisterHim();
        sendCommonOrderWithAuth();
        Response response = getOrderByUserWithAuth();
        checkIfResponseCodeOk(response);
        checkIfKeySuccessValueEqualsTrue(response);
    }

    @Test
    @Description("Негативный тест получения списка заказов неавторизованным пользователем")
    public void getOrderByUserNoAuthFail() {
        createNewUserAndRegisterHim();
        sendCommonOrderWithAuth();
        Response response = getOrderByUserWithoutAuth();
        checkIfResponseCodeUnauthorized(response);
        checkIfKeySuccessValueEqualsFalse(response);
        checkIfKeyMessageValueEqualsYouShouldBeAuthorised(response);
    }
}
