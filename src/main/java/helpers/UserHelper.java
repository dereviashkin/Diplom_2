package helpers;

import entities.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static date.Endpoints.getRegister;
import static helpers.GeneratorHelper.*;
import static helpers.JsonHelper.sendPostRequest;

public class UserHelper {

    @Step("Гененируем случаного пользователя")
    private static User generateRandomUser() {
        return new User(randomEmail(), randomName(), randomPassword());
    }

    @Step("Гененируем случаного пользователя без пароля")
    private static User generateRandomUserWithEmptyField(String field) {
        switch (field){
            case "email":
                return new User("", randomName(), randomPassword());
            case "name":
                return new User(randomEmail(), "", randomPassword());
            case "password":
                return new User(randomEmail(), randomName(), "");
            default:
                return null;
        }
    }

    @Step("Используем существующего пользователя")
    private static User existingUser() {
        return new User("test-data@yandex.ru", "Username", "password");
    }

    @Step("Берем заведомо существующие в базе данные и отправляем их на регистрацию")
    public static Response getExistingUserAndRegisterHim() {
        User user = existingUser();
        return sendPostRequest(user, getRegister());
    }

    @Step("Берем сгенерированные данные пользователя и отправляем их на регистрацию")
    public static Response getNewUserAndRegisterHim() {
        User user = generateRandomUser();
        return sendPostRequest(user, getRegister());
    }

    @Step("Берем сгенерированные данные пользователя с пустым полем и отправляем их на регистрацию")
    public static Response getNewUserWithEmptyFieldAndRegisterHim(String field) {
        User user = generateRandomUserWithEmptyField(field);
        return sendPostRequest(user, getRegister());
    }
}