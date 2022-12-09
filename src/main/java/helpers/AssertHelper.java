package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import static date.TextResponses.*;
import static helpers.JsonHelper.checkResponseCode;
import static helpers.JsonHelper.getValueByKey;

public class AssertHelper {

    @Step("Проверка, что статус код - ОК (200)")
    public static void checkResponseCodeOk(Response response) {
        Assert.assertEquals("Код ответа сервера не соответствует ожидаемому",
                HttpStatus.SC_OK,
                checkResponseCode(response));
    }

    @Step("Проверка, что статус код - Запрещено (403)")
    public static void checkIfResponseCodeForbidden(Response response) {
        Assert.assertEquals("Код ответа сервера не соответствует ожидаемому",
                HttpStatus.SC_FORBIDDEN,
                checkResponseCode(response));
    }

    @Step("Проверка, что статус код - Не авторизован (401)")
    public static void checkIfResponseCodeUnauthorized(Response response) {
        Assert.assertEquals("Код ответа сервера не соответствует ожидаемому",
                HttpStatus.SC_UNAUTHORIZED,
                checkResponseCode(response));
    }

    @Step("Проверка, что текст значения равен \"User already exists\"")
    public static void checkIfTextEqualsUserAlreadyExists(Response response) {
        Assert.assertEquals("Текст значения по ключу message не соответствет ожидаемому",
                getUserAlreadyExists(),
                getValueByKey(response, "message"));
    }

    @Step("Проверка, что текст значения равен \"Email, password and name are required fields\"")
    public static void checkIfTextEqualsSomeFieldsAreRequired(Response response) {
        Assert.assertEquals("Текст значения по ключу message не соответствет ожидаемому",
                getSomeFieldsAreRequired(),
                getValueByKey(response, "message"));
    }

    @Step("Проверка, что текст значения равен \"email or password are incorrect\"")
    public static void checkIfTextEqualsEmailOrPassAreIncorrect(Response response) {
        Assert.assertEquals("Текст значения по ключу success не соответствет ожидаемому",
                getEmailOrPassAreIncorrect(),
                getValueByKey(response, "message"));
    }

    @Step("Проверка, что текст значения равен \"true\"")
    public static void checkIfTextEqualsTrue(Response response) {
        Assert.assertEquals("Текст значения по ключу success не соответствет ожидаемому",
                "true",
                getValueByKey(response, "success"));
    }

    @Step("Проверка, что текст значения равен \"false\"")
    public static void checkIfTextEqualsFalse(Response response) {
        Assert.assertEquals("Текст значения по ключу success не соответствет ожидаемому",
                "false",
                getValueByKey(response, "success"));
    }
}