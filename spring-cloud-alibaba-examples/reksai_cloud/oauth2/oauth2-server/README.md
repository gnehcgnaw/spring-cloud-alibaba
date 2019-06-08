[TOC]

#### 基于内存存储令牌

##### 配置认证服务器

* 继承`AuthorizationServerConfigurerAdapter`重现`configure(ClientDetailsServiceConfigurer clients)`方法；

* 添加注解`@Configuration` 和` @EnableAuthorizationServer`。

```java
package red.reksai.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-08 22:34
 * @since :
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

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
          			//注册回调地址
                .redirectUris("http://www.baidu.com");
                //授权之后会携带code返回：https://www.baidu.com/?code=Ag2iOd
    }
}

```

##### 服务器安全配置

* 继承`WebSecurityConfigurerAdapter `重写`configure(AuthenticationManagerBuilder auth)`方法；
* 添加注解`@Configuration`、`@EnableWebSecurity`和`@EnableGlobalMethodSecurity(prePostEnabled = true ,securedEnabled = true ,jsr250Enabled = true)`；

````Java
package red.reksai.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-08 22:39
 * @since :
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true ,securedEnabled = true ,jsr250Enabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("123456")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("123456")).roles("USER");
    }
}

````

##### 访问获取授码

````html
http://<<ip>>:<<port>>/oauth/authorize?client_id=client&response_type=code
````

##### 通过授权码向服务器申请令牌

```sh
curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code=1JuO6V' "http://client:secret@localhost:8080/oauth/token"
```

##### 注意

出现`There is no PasswordEncoder mapped`的解决方案。

Spring Security 5.0 之前版本的 `PasswordEncoder` 接口默认实现为 `NoOpPasswordEncoder` 此时是可以使用明文密码的，在 5.0 之后默认实现类改为 `DelegatingPasswordEncoder` 此时密码必须以加密形式存储。

