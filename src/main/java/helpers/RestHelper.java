package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static data.Endpoints.userInfoEndpoint;
import static helpers.AuthHelper.getAccessToken;
import static helpers.UserHelper.changeRegisteredUserFieldRandom;
import static io.restassured.RestAssured.given;

public class RestHelper {

    @Step("Отправляем GET запрос без авторизации")
    public static Response sendGetRequestWithoutAuth(String endpoint) {
        return given()
                .header("Content-type", "application/json")
                .log().uri().and().log().body()
                .get(endpoint);
    }

    @Step("Отправляем GET запрос с авторизацией")
    public static Response sendGetRequestWithAuth(String token, String endpoint) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .log().uri().and().log().body()
                .get(endpoint);
    }

    @Step("Отправляем POST запрос без авторизации")
    public static Response sendPostRequestNoAuth(Object entity, String endpoint) {
        return given()
                .header("Content-type", "application/json")
                .log().uri().and().log().body()
                .body(entity)
                .post(endpoint);
    }

    @Step("Отправляем POST запрос с авторизацией")
    public static Response sendPostRequestWithAuth(String token, Object entity, String endpoint) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .log().uri().and().log().body()
                .body(entity)
                .post(endpoint);
    }

    @Step("Отправляем PATCH запрос без авторизации")
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
        return sendGetRequestWithAuth(getAccessToken(), userInfoEndpoint);
    }
}