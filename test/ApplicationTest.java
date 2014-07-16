import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
<<<<<<< HEAD
<<<<<<< HEAD
import models.*;
=======
>>>>>>> ad608050a8df8180026390907722eb76f5ac9475
=======
>>>>>>> ad608050a8df8180026390907722eb76f5ac9475

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }
    
}