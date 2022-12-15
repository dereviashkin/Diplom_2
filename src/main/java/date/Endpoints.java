package date;

public class Endpoints {
    //Получение данных об ингредиентах
    public static final String ingredientsEndpoint = "/ingredients";

    //Создание заказа
    public static final String ordersEndpoint = "/orders";
    //Получение всех заказов
    public static final String ordersAllEndpoint = ordersEndpoint + "/all";

    /**
     * Восстановление и сброс пароля
     * POST https://stellarburgers.nomoreparties.site/api/password-reset
     * Если ответ успешный, пользователь направляется на маршрут /reset-password . На
     * введённый имейл приходит инструкция с кодом для восстановления пароля.
     * На экране /reset-password пользователь вводит новый пароль и код из имейла, а
     * после нажимает кнопку «Сохранить». Происходит POST-запрос к эндпоинту
     * https://stellarburgers.nomoreparties.site/api/password-reset/reset .
     */
    public static final String passwordResetEndpoint = "/password-reset";
    public static final String resetPasswordEndpoint = "/reset-password";
    public static final String passwordResetResetEndpoint = passwordResetEndpoint + "/reset";

    //Авторизация и регистрация
    private static final String authEndpoint = "/auth";
    public static final String registerEndpoint = authEndpoint + "/register";
    public static final String loginEndpoint = authEndpoint + "/login";
    public static final String logoutEndpoint = authEndpoint + "/logout";
    public static final String tokenEndpoint = authEndpoint + "/token";

    //Пользователь
    public static final String userInfoEndpoint = authEndpoint + "/user";
}