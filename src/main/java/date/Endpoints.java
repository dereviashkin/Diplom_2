package date;

public class Endpoints {
    //Получение данных об ингредиентах
    public static final String ingredients = "/ingredients";

    //Создание заказа
    public static final String orders = "/orders";
    //Получение всех заказов
    public static final String ordersAll = orders + "/all";

    /**
     * Восстановление и сброс пароля
     * POST https://stellarburgers.nomoreparties.site/api/password-reset
     * Если ответ успешный, пользователь направляется на маршрут /reset-password . На
     * введённый имейл приходит инструкция с кодом для восстановления пароля.
     * На экране /reset-password пользователь вводит новый пароль и код из имейла, а
     * после нажимает кнопку «Сохранить». Происходит POST-запрос к эндпоинту
     * https://stellarburgers.nomoreparties.site/api/password-reset/reset .
     */
    public static final String passwordReset = "/password-reset";
    public static final String resetPassword = "/reset-password";
    public static final String passwordResetReset = passwordReset + "/reset";

    //Авторизация и регистрация
    private static final String auth = "/auth";
    public static final String register = auth + "/register";
    public static final String login = auth + "/login";
    public static final String logout = auth + "/logout";
    public static final String token = auth + "/token";

    //Пользователь
    public static final String userInfo = auth + "/user";
}
