package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static date.Endpoints.userInfoEndpoint;
import static helpers.AuthHelper.getAccessToken;
import static helpers.UserHelper.changeRegisteredUserFieldRandom;
import static io.restassured.RestAssured.given;

public class RestHelper {

    @Step("Отправляем POST запрос")
    public static Response sendPostRequest(Object entity, String endpoint) {
        return given()
                .header("Content-type", "application/json")
                .log().uri().and().log().body()
                .body(entity)
                .post(endpoint);
    }

    @Step("Отправляем GET запрос")
    public static Response sendGetRequest(String endpoint, String token) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .log().uri().and().log().body()
                .get(endpoint);
    }

    @Step("Отправляем PATCH запрос")
    public static Response sendPatchRequestNoAuth(Object entity, String endpoint) {
        return given()
                .header("Content-type", "application/json")
                .log().uri().and().log().body()
                .body(entity)
                .patch(endpoint);
    }

    @Step("Отправляем PATCH запрос с авторизацией")
    public static Response sendPatchRequestWithAuth(String token, Object entity, String endpoint) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .log().uri().and().log().body()
                .body(entity)
                .patch(endpoint);
    }

    @Step("Отправляем DELETE запрос с авторизацией")
    public static Response sendDeleteRequestWithAuth(String token, String endpoint) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .delete(endpoint);
    }

    @Step("Получаем код статуса ответа сервера")
    static int checkResponseCode(Response response) {
        System.out.println("Response:");
        response.then().log().body();
        return response.statusCode();
    }

    @Step("Получаем значение по ключу из тела ответа")
    public static String getValueByKey(Response response, String key) {
        return response.then().extract().body().path(key).toString();
    }

    @Step("Отправляем запрос на изменение выбранного поля пользователя с аутентификацией")
    public static Response sendPatchRequestChangeUserFieldWithAuth(String fieldToChange) {
        System.out.println("\nAccess Token: " + getAccessToken());
        return sendPatchRequestWithAuth(getAccessToken(), changeRegisteredUserFieldRandom(fieldToChange), userInfoEndpoint);
    }

    @Step("Отправляем запрос на изменение выбранного поля пользователя")
    public static Response sendPatchRequestChangeUserFieldNoAuth(String fieldToChange) {
        return sendPatchRequestNoAuth(changeRegisteredUserFieldRandom(fieldToChange), userInfoEndpoint);
    }

    @Step("Используя AccessToken созданного пользователя получаем информацию о нём")
    public static Response getRegisteredUserInfo() {
        return sendGetRequest(userInfoEndpoint, getAccessToken());
    }
}