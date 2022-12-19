import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static steps.CheckSteps.*;
import static steps.UserSteps.*;

public class LoginUserTest extends BaseTest {

    @Test
    @DisplayName("Позитивный тест авторизации существующей учетной записью")
    public void loginUserExistsSuccess() {
        createNewUserAndRegisterHim();
        Response response = getRegisteredUserAndLogin();
        checkIfResponseCodeOk(response);
        checkIfKeySuccessValueEqualsTrue(response);
    }

    @Test
    @DisplayName("Негативный тест авторизации с неверным паролем")
    public void loginUserExistsIncorrectPassFail() {
        createNewUserAndRegisterHim();
        Response response = getRegisteredUserIncorrectPasswordAndLogin();
        checkIfResponseCodeUnauthorized(response);
        checkIfKeySuccessValueEqualsFalse(response);
        checkIfKeyMessageValueEqualsEmailOrPassAreIncorrect(response);
    }

    @Test
    @DisplayName("Негативный тест авторизации с неверным email")
    public void loginUserExistsIncorrectEmailFail() {
        createNewUserAndRegisterHim();
        Response response = getRegisteredUserIncorrectEmailAndLogin();
        checkIfResponseCodeUnauthorized(response);
        checkIfKeySuccessValueEqualsFalse(response);
        checkIfKeyMessageValueEqualsEmailOrPassAreIncorrect(response);
    }
}