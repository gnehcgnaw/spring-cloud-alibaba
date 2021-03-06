package red.reksai.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * 基于rbac的数据库存储令牌的认证服务器
 *      继承并重写${@link AuthorizationServerConfigurerAdapter#configure(ClientDetailsServiceConfigurer)}方法
 *
 *
 *      @see ClientDetailsServiceConfigurer 定义客户端详细信息的配置器，可以初始化客户端详细信息，也可以引用数据库中的配置。
 *      @see  AuthorizationServerEndpointsConfigurer 定义授权和令牌端点以及令牌服务。
 *
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-08 22:34
 * @since :
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource datasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public TokenStore tokenStore() {
        return  new JdbcTokenStore(datasource());
    }

    @Bean
    public ClientDetailsService jdbcClientDetailService(){
        return new JdbcClientDetailsService(datasource());
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
        clients.withClientDetails(jdbcClientDetailService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore());
    }


}
