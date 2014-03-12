import com.xl.tools.FileUtil;
import org.junit.Test;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package PACKAGE_NAME
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 13-12-6 下午12:00
 * @Copyright: 2013 ihome.com
 */
public class FileUtilTest {

    @Test
    public void gzip() throws Exception {
        String inputFileName = "H:\\haoxiaolei\\Desktop\\VMware-workstation-full-10.0.1-1379776.exe";
        String outputFileName = "H:\\haoxiaolei\\Desktop\\VMware Workstation-full-10.0.1-1379776.gz";
        FileUtil.gzip(inputFileName, outputFileName);
    }
}
