package helpers;

import entities.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static data.Endpoints.ordersEndpoint;
import static helpers.AuthHelper.getAccessToken;
import static helpers.GeneratorHelper.randomString;
import static helpers.RestHelper.*;

public class OrderHelper {

    //TODO в шаг @Before добавить получение всех ингров, десериализовать через класс Ingredients, сохранять как массив экземпляров класса,
    // формировать рандомную булку по значению ключа type: случайный bun + случайный main + случайный sauce
    @Step("Создаем обычный заказ")
    public static Order createCommonOrder() {
        String[] ingredients = {"61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f"};
        return new Order(ingredients);
    }

    @Step("Создаем заказ с неверным хешем ингредиентов")
    public static Order createIncorrectHashOrder() {
        String[] ingredients = {randomString(6), randomString(6)};
        return new Order(ingredients);
    }

    @Step("Создаем пустой заказ")
    public static Order createEmptyOrder() {
        String[] ingredients = {};
        return new Order(ingredients);
    }

    @Step("Отправляем обычный заказ")
    public static Response sendCommonOrder() {
        return sendPostRequestNoAuth(createCommonOrder(), ordersEndpoint);
    }

    @Step("Отправляем обычный заказ с авторизацией")
    public static Response sendCommonOrderWithAuth() {
        return sendPostRequestWithAuth(getAccessToken(), createCommonOrder(), ordersEndpoint);
    }

    @Step("Отправляем заказ с неверным хешем ингредиентов")
    public static Response sendIncorrectHashOrder() {
        return sendPostRequestNoAuth(createIncorrectHashOrder(), ordersEndpoint);
    }

    @Step("Отправляем пустой заказ")
    public static Response sendEmptyOrder() {
        return sendPostRequestNoAuth(createEmptyOrder(), ordersEndpoint);
    }

    @Step("Отправляем пустой заказ с авторизацией")
    public static Response sendEmptyOrderWithAuth() {
        return sendPostRequestWithAuth(getAccessToken(), createEmptyOrder(), ordersEndpoint);
    }

    @Step("Отправляем запрос на список заказов авторизованным пользователем")
    public static Response getOrderByUserWithAuth() {
        return sendGetRequestWithAuth(getAccessToken(), ordersEndpoint);
    }

    @Step("Получаем запрос на список заказов неавторизованным пользователем")
    public static Response getOrderByUserWithoutAuth() {
        return sendGetRequestWithoutAuth(ordersEndpoint);
    }
}