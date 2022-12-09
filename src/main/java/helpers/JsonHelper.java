package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class JsonHelper {

    @Step("Отправляем POST запрос")
    public static Response sendPostRequest(Object entity, String endpoint) {
        return given()
                .header("Content-type", "application/json")
                .log().uri().and().log().body()
                .body(entity)
                .post(endpoint);
    }

    @Step("Выводим тела ответа")
    public static void printResponse(Response response) {
        System.out.println("\n\rResponse:");
        response.prettyPrint();
    }

    @Step("Получаем код статуса ответа сервера")
    static int checkResponseCode(Response response) {
        return response.statusCode();
    }

    @Step("Получаем значение по ключу из тела ответа")
    public static String getValueByKey(Response response, String key) {
        return response.then().log().body().extract().body().path(key).toString();
    }
}