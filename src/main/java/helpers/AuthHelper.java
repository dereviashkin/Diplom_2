package helpers;

import entities.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class AuthHelper {
    private static String password;
    private static String email;

    public static String getAccessToken() {
        return AccessToken;
    }

    private static String AccessToken;

    public static String getPassword() {
        return password;
    }

    public static String getEmail() {
        return email;
    }

    @Step("Сохраняем почту и пароль для последующего логина")
    public static void saveEmailAndPassword(User user) {
        email = user.getEmail();
        password = user.getPassword();
    }

    @Step("Сохраняем токет аутентификации для входа")
    public static void saveAccessToken(Response response) {
        AccessToken = response.then().extract().body().path("accessToken");
    }
}
