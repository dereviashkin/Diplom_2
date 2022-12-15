package helpers;

import entities.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static date.Endpoints.login;
import static date.Endpoints.register;
import static helpers.AuthHelper.*;
import static helpers.GeneratorHelper.*;
import static helpers.RestHelper.sendPostRequest;

public class UserHelper {

    @Step("Гененируем случаного пользователя")
    private static User generateRandomUser() {
        return new User(randomName(), randomEmail(), randomPassword());
    }

    @Step("Гененируем случаного пользователя без одного из полей")
    private static User generateRandomUserWithEmptyField(String field) {
        User user;
        switch (field) {
            case "name":
                user = new User("", randomEmail(), randomPassword());
                break;
            case "email":
                user = new User(randomName(), "", randomPassword());
                break;
            case "password":
                user = new User(randomName(), randomEmail(), "");
                break;
            default:
                throw new RuntimeException("Задано неизвестное поле для User");
        }
        return user;
    }

    @Step("Используем существующего пользователя")
    private static User existingUser() {
        return new User("Username", "test-data@yandex.ru", "password");
    }

    @Step("Берем заведомо существующие в базе данные и отправляем их на регистрацию")
    public static Response createExistingUserAndRegisterHim() {
        User user = existingUser();
        return sendPostRequest(user, register);
    }

    @Step("Берем сгенерированные данные пользователя и отправляем их на регистрацию")
    public static Response createNewUserAndRegisterHim() {
        User user = generateRandomUser();
        saveEmailAndPassword(user);
        Response response = sendPostRequest(user, register);
        saveAccessToken(response);
        return response;
    }

    @Step("Берем сгенерированные данные пользователя с пустым полем и отправляем их на регистрацию")
    public static Response createNewUserWithEmptyFieldAndRegisterHim(String field) {
        User user = generateRandomUserWithEmptyField(field);
        saveEmailAndPassword(user);
        return sendPostRequest(user, register);
    }

    //TODO переделать: нужен текущий экземпляр класса User, а не новый
    @Step("Берем созданного пользователя и логинимся им")
    public static Response getRegisteredUserAndLogin() {
        User user = new User(getEmail(), getPassword());
        return sendPostRequest(user, login);
    }

    //TODO переделать: нужен текущий экземпляр класса User с обнуленным полем, а не новый
    @Step("Берем созданного пользователя, ломаем пароль и логинимся им")
    public static Response getRegisteredUserIncorrectPasswordAndLogin() {
        User user = new User(getEmail(), "");
        return sendPostRequest(user, login);
    }

    //TODO переделать: нужен текущий экземпляр класса User с обнуленным полем, а не новый
    @Step("Берем созданного пользователя, ломаем email и логинимся им")
    public static Response getRegisteredUserIncorrectEmailAndLogin() {
        User user = new User("", getPassword());
        return sendPostRequest(user, login);
    }

    //TODO переделать: нужен текущий экземпляр класса User с рандомным полем, а не новый
    @Step("Берем созданного пользователя и меняем ему имя")
    public static User changeRegisteredUserFieldRandom(String fieldToRandom) {
        User user;
        switch (fieldToRandom) {
            case "name":
                user = new User(randomName(), null, null);
                break;
            case "email":
                user = new User(null, randomEmail(), null);
                break;
            case "password":
                user = new User(null, null, randomPassword());
                break;
            default:
                throw new RuntimeException("Задано неизвестное поле для User");
        }
        return user;
    }
}