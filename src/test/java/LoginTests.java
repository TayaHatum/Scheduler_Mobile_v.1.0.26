
import com.telran.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{


    @Test
    public void testLogin(){
        app.getUserHelper().logIn(new User()
                .withUserName("hatum@mail.com")
                .withPassword("123456Aa"));
        app.getUserHelper().skipSettings();
        Assert.assertTrue(app.getUserHelper().isLoggedIn());
    }
}
