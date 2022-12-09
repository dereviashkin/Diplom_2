import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;

public class BaseTest {

    @Before
    public void before(){
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api";
    }

    @After
    public void after(){

    }
}
