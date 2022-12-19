import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static steps.CheckSteps.*;
import static steps.RestSteps.*;
import static steps.UserSteps.createNewUserAndRegisterHim;

public class ChangeUserDataTest extends BaseTest {

    @Test
    @DisplayName("Позитивный тест получения информации о зарегистрированном пользователе")
    public void getInfoCorrectSuccess() {
        createNewUserAndRegisterHim();
        Response response = getRegisteredUserInfo();
        checkIfResponseCodeOk(response);
        checkIfKeySuccessValueEqualsTrue(response);
    }

    @Test
    @DisplayName("Позитивный тест изменения имени зарегистрированного пользователя с аутентификацией")
    public void patchNameCorrectSuccess() {
        createNewUserAndRegisterHim();
        Response response = sendPatchRequestChangeUserFieldWithAuth("name");
        checkIfResponseCodeOk(response);
        checkIfKeySuccessValueEqualsTrue(response);
    }

    @Test
    @DisplayName("Позитивный тест изменения email зарегистрированного пользователя с аутентификацией")
    public void patchEmailCorrectSuccess() {
        createNewUserAndRegisterHim();
        Response response = sendPatchRequestChangeUserFieldWithAuth("email");
        checkIfResponseCodeOk(response);
        checkIfKeySuccessValueEqualsTrue(response);
    }

    @Test
    @DisplayName("Позитивный тест изменения пароля зарегистрированного пользователя с аутентификацией")
    public void patchPasswordCorrectSuccess() {
        createNewUserAndRegisterHim();
        Response response = sendPatchRequestChangeUserFieldWithAuth("password");
        checkIfResponseCodeOk(response);
        checkIfKeySuccessValueEqualsTrue(response);
    }

    @Test
    @DisplayName("Негативный тест изменения имени зарегистрированного пользователя, но без аутентификации")
    public void patchNameNoAuthFail() {
        createNewUserAndRegisterHim();
        Response response = sendPatchRequestChangeUserFieldNoAuth("name");
        checkIfResponseCodeUnauthorized(response);
        checkIfKeySuccessValueEqualsFalse(response);
        checkIfKeyMessageValueEqualsYouShouldBeAuthorised(response);
    }

    @Test
    @DisplayName("Негативный тест изменения email зарегистрированного пользователя, но без аутентификации")
    public void patchEmailNoAuthFail() {
        createNewUserAndRegisterHim();
        Response response = sendPatchRequestChangeUserFieldNoAuth("email");
        checkIfResponseCodeUnauthorized(response);
        checkIfKeySuccessValueEqualsFalse(response);
        checkIfKeyMessageValueEqualsYouShouldBeAuthorised(response);
    }

    @Test
    @DisplayName("Негативный тест изменения пароля зарегистрированного пользователя, но без аутентификации")
    public void patchPasswordNoAuthFail() {
        createNewUserAndRegisterHim();
        Response response = sendPatchRequestChangeUserFieldNoAuth("password");
        checkIfResponseCodeUnauthorized(response);
        checkIfKeySuccessValueEqualsFalse(response);
        checkIfKeyMessageValueEqualsYouShouldBeAuthorised(response);
    }
}
