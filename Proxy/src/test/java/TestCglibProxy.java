import com.iunet.cglib_proxy.proxy.CheckPermissionProxy;
import com.iunet.cglib_proxy.service.impl.LoginServiceImpl;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: haoxiaolei
 * Date: 13-7-4
 * Time: 下午6:37
 * To change this template use File | Settings | File Templates.
 */
public class TestCglibProxy {

    @Test
    public void testCglibProxy() {
        CheckPermissionProxy proxy = new CheckPermissionProxy();
        LoginServiceImpl loginService = (LoginServiceImpl) proxy.getInstance(new LoginServiceImpl());
        loginService.addLoginLogs();
    }
}
