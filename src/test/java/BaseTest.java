import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.BeforeClass;

import static date.Endpoints.userInfoEndpoint;
import static helpers.AssertHelper.checkIfKeyMessageValueEqualsUserSuccessfullyRemoved;
import static helpers.AssertHelper.checkIfResponseCodeAccepted;
import static helpers.AuthHelper.getAccessToken;
import static helpers.RestHelper.sendDeleteRequestWithAuth;

public class BaseTest {

    @BeforeClass
    public static void beforeClass() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api";
    }

    @After
    public void after() {
        if (getAccessToken() != null) {
            System.out.println("\n--== After each testcase ==--");
            Response response = sendDeleteRequestWithAuth(getAccessToken(), userInfoEndpoint);
            checkIfResponseCodeAccepted(response);
            checkIfKeyMessageValueEqualsUserSuccessfullyRemoved(response);
        }
    }
}