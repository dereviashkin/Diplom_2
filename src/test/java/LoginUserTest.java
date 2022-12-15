import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.Test;

import static helpers.AssertHelper.*;
import static helpers.UserHelper.*;

public class LoginUserTest extends BaseTest {

    @Test
    @Description("Позитивный тест авторизации существующей учетной записью")
    public void loginUserExistsSuccess() {
        createNewUserAndRegisterHim();
        Response response = getRegisteredUserAndLogin();
        checkIfResponseCodeOk(response);
        checkIfKeySuccessValueEqualsTrue(response);
    }

    @Test
    @Description("Негативный тест авторизации с неверным паролем")
    public void loginUserExistsIncorrectPassFail() {
        createNewUserAndRegisterHim();
        Response response = getRegisteredUserIncorrectPasswordAndLogin();
        checkIfResponseCodeUnauthorized(response);
        checkIfKeySuccessValueEqualsFalse(response);
        checkIfKeyMessageValueEqualsEmailOrPassAreIncorrect(response);
    }

    @Test
    @Description("Негативный тест авторизации с неверным email")
    public void loginUserExistsIncorrectEmailFail() {
        createNewUserAndRegisterHim();
        Response response = getRegisteredUserIncorrectEmailAndLogin();
        checkIfResponseCodeUnauthorized(response);
        checkIfKeySuccessValueEqualsFalse(response);
        checkIfKeyMessageValueEqualsEmailOrPassAreIncorrect(response);
    }
}