package date;

public class TextResponses {
    //Создание пользователя
    private static String userAlreadyExists = "User already exists";
    private static String someFieldsAreRequired = "Email, password and name are required fields";

    //Авторизация пользователя
    private static String emailOrPassAreIncorrect = "email or password are incorrect";
    private static String youShouldBeAuthorised = "You should be authorised";
    private static String userWithEmailAlreadyExists = "User with such email already exists";

    //Заказы
    private static String ingredientIdMustBeProvided = "Ingredient ids must be provided";

    //Сброс пароля
    private static String resetEmailSent = "Reset email sent";

    public static String getUserAlreadyExists() {
        return userAlreadyExists;
    }

    public static String getSomeFieldsAreRequired() {
        return someFieldsAreRequired;
    }

    public static String getEmailOrPassAreIncorrect() {
        return emailOrPassAreIncorrect;
    }

    public static String getYouShouldBeAuthorised() {
        return youShouldBeAuthorised;
    }

    public static String getUserWithEmailAlreadyExists() {
        return userWithEmailAlreadyExists;
    }

    public static String getIngredientIdMustBeProvided() {
        return ingredientIdMustBeProvided;
    }

    public static String getResetEmailSent() {
        return resetEmailSent;
    }
}
