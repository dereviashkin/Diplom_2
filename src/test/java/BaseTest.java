import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.BeforeClass;

import static data.Endpoints.userInfoEndpoint;
import static steps.CheckSteps.checkIfKeyMessageValueEqualsUserSuccessfullyRemoved;
import static steps.CheckSteps.checkIfResponseCodeAccepted;
import static steps.AuthSteps.getAccessToken;
import static steps.AuthSteps.nullToken;
import static steps.RestSteps.sendDeleteRequestWithAuth;

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
            nullToken();
        }
    }
}