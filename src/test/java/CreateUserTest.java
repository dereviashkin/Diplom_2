import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.Test;

import static helpers.AssertHelper.*;
import static helpers.UserHelper.*;

public class CreateUserTest extends BaseTest {

    @Test
    @Description("Негативный тест создания пользователя, который уже есть в базе")
    public void createUserExistsFail() {
        Response response = createExistingUserAndRegisterHim();
        checkIfResponseCodeForbidden(response);
        checkIfTextEqualsUserAlreadyExists(response);
    }

    @Test
    @Description("Позитивный тест создания нового пользователя")
    public void createUserNewSuccess() {
        Response response = createNewUserAndRegisterHim();
        checkResponseCodeOk(response);
        checkIfTextEqualsTrue(response);
    }

    @Test
    @Description("Негативный тест создания нового пользователя с незаполненным email")
    public void createUserNewWithEmptyPasswordFail() {
        Response response = createNewUserWithEmptyFieldAndRegisterHim("password");
        checkIfResponseCodeForbidden(response);
        checkIfTextEqualsSomeFieldsAreRequired(response);
    }

    @Test
    @Description("Негативный тест создания нового пользователя с незаполненным именем")
    public void createUserNewWithEmptyNameFail() {
        Response response = createNewUserWithEmptyFieldAndRegisterHim("name");
        checkIfResponseCodeForbidden(response);
        checkIfTextEqualsSomeFieldsAreRequired(response);
    }
    @Test
    @Description("Негативный тест создания нового пользователя с незаполненным паролем")
    public void createUserNewWithEmptyEmailFail() {
        Response response = createNewUserWithEmptyFieldAndRegisterHim("email");
        checkIfResponseCodeForbidden(response);
        checkIfTextEqualsSomeFieldsAreRequired(response);
    }
}