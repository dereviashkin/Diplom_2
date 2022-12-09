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

    @Step("Получаем код статуса ответа сервера")
    static int checkResponseCode(Response response) {
        System.out.println("\nResponse:");
        response.then().log().body();
        return response.statusCode();
    }

    @Step("Получаем значение по ключу из тела ответа")
    public static String getValueByKey(Response response, String key) {
        return response.then().extract().body().path(key).toString();
    }
}