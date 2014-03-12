import com.iunet.itext.helloworld.HelloWorld;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: xiaolei
 * Date: 13-8-8
 * Time: 下午10:45
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldTest {

    private HelloWorld helloWorld = new HelloWorld();

    @Test
    public void createSimplePdf() {
        helloWorld.createSimplePdf(System.getProperty("user.dir") + "/iText/result", "helloworld.pdf");
    }

    @Test
    public void createRectanglePdf() {
        helloWorld.createRectanglePdf(System.getProperty("user.dir") + "/iText/result", "hello_rectangle.pdf");
    }
















}
