package red.reksai.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 基于内存存储令牌的认证服务器
 *      继承并重写${@link AuthorizationServerConfigurerAdapter#configure(ClientDetailsServiceConfigurer)}方法
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-08 22:34
 * @since :
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * spring security 5.0之后，必须使用密码加密模式，不然会出现"There is no PasswordEncoder mapped"错误。
     *  因为在${@link WebSecurityConfiguration}中注入了@Bean passwordEncoder，所以这里可以直接使用。
     */
    private BCryptPasswordEncoder passwordEncoder ;

    public AuthorizationServerConfiguration(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *
     * @param clients
     * @throws Exception
     *
     * 认证访问地址：http:ip:<<port>>/oauth/authorize?client_id=client&response_type=code
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients
                .inMemory()
                //
                .withClient("client")
                //密钥
                .secret(passwordEncoder.encode("secret"))
                //授权类型
                .authorizedGrantTypes("authorization_code")
                //授权范围
                .scopes("app")
                .redirectUris("http://www.baidu.com");
                //授权之后会携带code返回：https://www.baidu.com/?code=Ag2iOd
    }
}
