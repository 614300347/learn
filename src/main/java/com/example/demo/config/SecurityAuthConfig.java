package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @ClassName:SecurityAuthConfig
 * @Author:LiGou
 * @Date:2023/6/9 8:53
 * @Version:1.0
 * @Description:
 */
@Configuration
public class SecurityAuthConfig {
    //注入过滤链条
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// http.authorizeHttpRequests //授权http请求
//                (new Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>() {
//                     @Override
//                     public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authz) {
//                         authz.anyRequest() //任何请求
////                                 .denyAll();//拒绝所有
//                                 .authenticated();  //需要认证
//                     }
//                 }
//                )
//                .httpBasic(withDefaults());
        http.authorizeHttpRequests().mvcMatchers("/security/**")  //匹配url
                .hasAnyAuthority("student:delete") //拥有这个权限的用户能访问上面的url
                .mvcMatchers("/testredis")  //同上
                .hasAnyAuthority("ROLE_teacher")
                .anyRequest()  //任何请求
                .authenticated(); //需要认证
        http.formLogin().permitAll();
        return http.build();
    }

}


