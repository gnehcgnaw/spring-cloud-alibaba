package red.reksai.providergithubservice;

import org.junit.Test;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-05-06 19:02
 * @since :
 */
public class GetOsName {
    @Test
    public void getUserOsName(){
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("java.class.path"));
    }
}
