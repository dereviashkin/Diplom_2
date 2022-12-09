package date;

public class Endpoints {
    //Получение данных об ингредиентах
    private static String ingredients = "/ingredients";

    //Создание заказа
    private static String orders = "/orders";
    //Получение всех заказов
    private static String ordersAll = orders + "/all";

    /**
     * Восстановление и сброс пароля
     * POST https://stellarburgers.nomoreparties.site/api/password-reset
     * Если ответ успешный, пользователь направляется на маршрут /reset-password . На
     * введённый имейл приходит инструкция с кодом для восстановления пароля.
     * На экране /reset-password пользователь вводит новый пароль и код из имейла, а
     * после нажимает кнопку «Сохранить». Происходит POST-запрос к эндпоинту
     * https://stellarburgers.nomoreparties.site/api/password-reset/reset .
     */
    private static String passwordReset = "/password-reset";
    private static String resetPassword = "/reset-password";
    private static String passwordResetReset = "/password-reset/reset";

    //Авторизация и регистрация
    private static String register = "/auth/register";
    private static String login = "/auth/login";
    private static String logout = "/auth/logout";
    private static String token = "/auth/token";

    //Пользователь
    private static String user = "/auth/user";

    public static String getIngredients() {
        return ingredients;
    }

    public static String getOrders() {
        return orders;
    }

    public static String getOrdersAll() {
        return ordersAll;
    }

    public static String getPasswordReset() {
        return passwordReset;
    }

    public static String getResetPassword() {
        return resetPassword;
    }

    public static String getPasswordResetReset() {
        return passwordResetReset;
    }

    public static String getLogin() {
        return login;
    }

    public static String getLogout() {
        return logout;
    }

    public static String getRegister() {
        return register;
    }

    public static String getToken() {
        return token;
    }

    public static String getUser() {
        return user;
    }
}
