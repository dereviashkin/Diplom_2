import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.Test;

import static helpers.AssertHelper.*;
import static helpers.OrderHelper.*;
import static helpers.UserHelper.createNewUserAndRegisterHim;

public class CreateOrderTest extends BaseTest {

    @Test
    @Description("Позитивный тест создания заказа без авторизации с ингредиентами")
    public void createCommonOrderWithoutAuthSuccess() {
        Response response = sendCommonOrder();
        checkIfResponseCodeOk(response);
        checkIfKeySuccessValueEqualsTrue(response);
    }

    @Test
    @Description("Негативный тест создания заказа без авторизации без ингредиентов")
    public void createEmptyOrderWithoutAuthFail() {
        Response response = sendEmptyOrder();
        checkIfResponseCodeBadRequest(response);
        checkIfKeySuccessValueEqualsFalse(response);
        checkIfKeyMessageValueEqualsIngredientIdMustBeProvided(response);
    }

    @Test
    @Description("Негативный тест создания заказа с неверным хешем ингредиентов")
    public void createIncorrectHashOrderWithoutAuthFail() {
        Response response = sendIncorrectHashOrder();
        checkIfResponseCodeInternalServerError(response);
    }

    @Test
    @Description("Позитивный тест создания заказа с авторизацией с ингредиентами")
    public void createCommonOrderWithAuthSuccess() {
        createNewUserAndRegisterHim();
        Response response = sendCommonOrderWithAuth();
        checkIfResponseCodeOk(response);
        checkIfKeySuccessValueEqualsTrue(response);
    }

    @Test
    @Description("Негативный тест создания заказа с авторизацией без ингредиентов")
    public void createEmptyOrderWithAuthSuccess() {
        createNewUserAndRegisterHim();
        Response response = sendEmptyOrderWithAuth();
        checkIfResponseCodeBadRequest(response);
        checkIfKeySuccessValueEqualsFalse(response);
        checkIfKeyMessageValueEqualsIngredientIdMustBeProvided(response);
    }
}