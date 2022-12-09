package helpers;

import entities.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static date.Endpoints.getLogin;
import static date.Endpoints.getRegister;
import static helpers.GeneratorHelper.*;
import static helpers.JsonHelper.sendPostRequest;

public class UserHelper {

    private static String password;
    private static String email;

    @Step("Гененируем случаного пользователя")
    private static User generateRandomUser() {
        return new User(randomName(), randomEmail(), randomPassword());
    }

    @Step("Гененируем случаного пользователя без одного из полей")
    private static User generateRandomUserWithEmptyField(String field) {
        switch (field) {
            case "name":
                return new User("", randomEmail(), randomPassword());
            case "email":
                return new User(randomName(), "", randomPassword());
            case "password":
                return new User(randomName(), randomEmail(), "");
//            default:
//                return new User(randomName(), randomEmail(), randomPassword());
        }
        //TODO костыль, не знаю пока как поправить
        return null;
    }

    @Step("Используем существующего пользователя")
    private static User existingUser() {
        return new User("Username", "test-data@yandex.ru", "password");
    }

    @Step("Берем заведомо существующие в базе данные и отправляем их на регистрацию")
    public static Response createExistingUserAndRegisterHim() {
        User user = existingUser();
        return sendPostRequest(user, getRegister());
    }

    @Step("Берем сгенерированные данные пользователя и отправляем их на регистрацию")
    public static Response createNewUserAndRegisterHim() {
        User user = generateRandomUser();
        saveEmailAndPassword(user);
        return sendPostRequest(user, getRegister());
    }

    @Step("Берем сгенерированные данные пользователя с пустым полем и отправляем их на регистрацию")
    public static Response createNewUserWithEmptyFieldAndRegisterHim(String field) {
        User user = generateRandomUserWithEmptyField(field);
        saveEmailAndPassword(user);
        return sendPostRequest(user, getRegister());
    }

    @Step("Сохраняем почту и пароль для последующего логина")
    public static void saveEmailAndPassword(User user) {
        email = user.getEmail();
        password = user.getPassword();
    }

    @Step("Берем созданного пользователя и логинимся им")
    public static Response getRegisteredUserAndLogin() {
        User user = new User(email, password);
        return sendPostRequest(user, getLogin());
    }

    @Step("Берем созданного пользователя, ломаем пароль и логинимся им")
    public static Response getRegisteredUserIncorrectPasswordAndLogin() {
        User user = new User(email, "");
        return sendPostRequest(user, getLogin());
    }

    @Step("Берем созданного пользователя, ломаем email и логинимся им")
    public static Response getRegisteredUserIncorrectEmailAndLogin() {
        User user = new User("", password);
        return sendPostRequest(user, getLogin());
    }
}