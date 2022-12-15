package helpers;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import java.util.UUID;

public class GeneratorHelper {

    private static String name;

    @Step("Генерируем строку с помощью UUID указанной длины")
    public static String randomString(int length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }

    @Step("Генерируем пароль длиной 8 знаков")
    public static String randomPassword() {
        return randomString(8);
    }

    @Step("Генерируем имя пользователя")
    public static String randomName() {
        name = new Faker().name().firstName();
        return name;
    }

    @Step("Генерируем почтовый ящик")
    public static String randomEmail() {
        if (name != null) {
            return name.toLowerCase() + randomString(3) + "@yandex.ru";
        } else return randomString(3) + "@yandex.ru";
    }
}