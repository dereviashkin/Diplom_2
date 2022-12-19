import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static steps.CheckSteps.*;
import static steps.UserSteps.*;

public class CreateUserTest extends BaseTest {

    @Test
    @DisplayName("Негативный тест создания пользователя, который уже есть в базе")
    public void createUserExistsFail() {
        Response response = createExistingUserAndRegisterHim();
        checkIfResponseCodeForbidden(response);
        checkIfKeyMessageValueEqualsUserAlreadyExists(response);
    }

    @Test
    @DisplayName("Позитивный тест создания нового пользователя")
    public void createUserNewSuccess() {
        Response response = createNewUserAndRegisterHim();
        checkIfResponseCodeOk(response);
        checkIfKeySuccessValueEqualsTrue(response);
    }

    @Test
    @DisplayName("Негативный тест создания нового пользователя с незаполненным email")
    public void createUserNewWithEmptyPasswordFail() {
        Response response = createNewUserWithEmptyFieldAndRegisterHim("password");
        checkIfResponseCodeForbidden(response);
        checkIfKeyMessageValueEqualsSomeFieldsAreRequired(response);
    }

    @Test
    @DisplayName("Негативный тест создания нового пользователя с незаполненным именем")
    public void createUserNewWithEmptyNameFail() {
        Response response = createNewUserWithEmptyFieldAndRegisterHim("name");
        checkIfResponseCodeForbidden(response);
        checkIfKeyMessageValueEqualsSomeFieldsAreRequired(response);
    }

    @Test
    @DisplayName("Негативный тест создания нового пользователя с незаполненным паролем")
    public void createUserNewWithEmptyEmailFail() {
        Response response = createNewUserWithEmptyFieldAndRegisterHim("email");
        checkIfResponseCodeForbidden(response);
        checkIfKeyMessageValueEqualsSomeFieldsAreRequired(response);
    }
}