import com.iunet.jdk_proxy.proxy.CheckPermissionProxy;
import com.iunet.jdk_proxy.service.LoginService;
import com.iunet.jdk_proxy.service.impl.LoginServiceImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 * User: haoxiaolei
 * Date: 13-7-3
 * Time: 下午4:31
 * To change this template use File | Settings | File Templates.
 */
public class TestJdkProxy {

    @Test
    public void testProxy() {
        LoginService loginService = new LoginServiceImpl();
        CheckPermissionProxy permissionInterceptor = new CheckPermissionProxy();
        permissionInterceptor.setTarget(loginService);
        LoginService loginServiceProxy = (LoginService) Proxy.newProxyInstance(loginService.getClass().getClassLoader(), loginService.getClass().getInterfaces(), permissionInterceptor);
        loginServiceProxy.login();
        loginServiceProxy.logout();
    }

}
