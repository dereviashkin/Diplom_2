package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import static date.TextResponses.*;
import static helpers.RestHelper.checkResponseCode;
import static helpers.RestHelper.getValueByKey;

public class AssertHelper {

    @Step("Проверяем, что статус код - ОК (200)")
    public static void checkIfResponseCodeOk(Response response) {
        Assert.assertEquals("Код ответа сервера не соответствует ожидаемому",
                HttpStatus.SC_OK,
                checkResponseCode(response));
    }

    @Step("Проверяем, что статус код - Запрещено (403)")
    public static void checkIfResponseCodeForbidden(Response response) {
        Assert.assertEquals("Код ответа сервера не соответствует ожидаемому",
                HttpStatus.SC_FORBIDDEN,
                checkResponseCode(response));
    }

    @Step("Проверяем, что статус код - Не авторизован (401)")
    public static void checkIfResponseCodeUnauthorized(Response response) {
        Assert.assertEquals("Код ответа сервера не соответствует ожидаемому",
                HttpStatus.SC_UNAUTHORIZED,
                checkResponseCode(response));
    }

    @Step("Проверяем, что значение по ключу \"message\" равно \"User already exists\"")
    public static void checkIfKeyMessageValueEqualsUserAlreadyExists(Response response) {
        Assert.assertEquals("Текст значения по ключу message не соответствет ожидаемому",
                getUserAlreadyExists(),
                getValueByKey(response, "message"));
    }

    @Step("Проверяем, что значение по ключу \"message\" равно \"Email, password and name are required fields\"")
    public static void checkIfKeyMessageValueEqualsSomeFieldsAreRequired(Response response) {
        Assert.assertEquals("Текст значения по ключу message не соответствет ожидаемому",
                getSomeFieldsAreRequired(),
                getValueByKey(response, "message"));
    }

    @Step("Проверяем, что значение по ключу \"message\" равно \"email or password are incorrect\"")
    public static void checkIfKeyMessageValueEqualsEmailOrPassAreIncorrect(Response response) {
        Assert.assertEquals("Текст значения по ключу success не соответствет ожидаемому",
                getEmailOrPassAreIncorrect(),
                getValueByKey(response, "message"));
    }

    @Step("Проверяем, что значение по ключу \"message\" равно \"You should be authorised\"")
    public static void checkIfKeyMessageValueEqualsYouShouldBeAuthorised(Response response) {
        Assert.assertEquals("Текст значения по ключу success не соответствет ожидаемому",
                getYouShouldBeAuthorised(),
                getValueByKey(response, "message"));
    }

    @Step("Проверяем, что значение по ключу \"success\" равно \"true\"")
    public static void checkIfKeySuccessValueEqualsTrue(Response response) {
        Assert.assertEquals("Текст значения по ключу success не соответствет ожидаемому",
                "true",
                getValueByKey(response, "success"));
    }

    @Step("Проверяем, что значение по ключу \"success\" равно \"false\"")
    public static void checkIfKeySuccessValueEqualsFalse(Response response) {
        Assert.assertEquals("Текст значения по ключу success не соответствет ожидаемому",
                "false",
                getValueByKey(response, "success"));
    }
}