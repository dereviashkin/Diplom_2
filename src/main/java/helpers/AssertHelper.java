package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import static date.TextResponses.getSomeFieldsAreRequired;
import static date.TextResponses.getUserAlreadyExists;
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

    @Step("Проверка, что текст значения равен \"true\"")
    public static void checkIfTextEqualsTrue(Response response) {
        Assert.assertEquals("Текст значения по ключу success не соответствет ожидаемому",
                "true",
                getValueByKey(response, "success"));
    }
}