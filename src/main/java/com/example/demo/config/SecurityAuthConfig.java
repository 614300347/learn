package com.example.demo.config;

import com.example.demo.service.impl.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.PasswordManagementConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;

/**
 * @ClassName:SecurityAuthConfig
 * @Author:LiGou
 * @Date:2023/6/9 8:53
 * @Version:1.0
 * @Description:
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled=true)
public class SecurityAuthConfig {

    @Resource
    private MyAuthecationHandler myAuthecationHandler;

    @Autowired
    private SecurityNotAccessHandler secutiryNotAccessHandler;
    @Autowired
    private SecurityLogOutHandler securityLogOutHandler;

    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsServiceImpl;




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


        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").defaultSuccessUrl("/index.html")
//                .and().authorizeHttpRequests().mvcMatchers("/security/**")  //匹配url
//                .hasAnyAuthority("student:delete") //拥有这个权限的用户能访问上面的url
//                .mvcMatchers("/doc.html")
//                .hasAnyAuthority("ROLE_ADMIN")
//                .mvcMatchers("/testredis")  //同上
//                .hasAnyAuthority("ROLE_ADMIN")
//                .mvcMatchers("/authtest1")
//                .hasAnyAuthority("ROLE_ADMIN")
//                .mvcMatchers("/testredis1/**")
//                .hasAnyAuthority("ROLE_ADMIN")
//                .mvcMatchers("//lesson/**")
//                .hasAnyAuthority("ROLE_ADMIN")
                .and()
                .authorizeHttpRequests()
                .mvcMatchers("/login.html", "/index.html", "/login").permitAll()
                .anyRequest()  //任何请求
                .authenticated() //需要认证
                .and().csrf().disable(); //关闭crsf保护，不然会拦截所有post请求提示无权限
//        http.userDetailsService(myUserDetailsServiceImpl).passwordManagement()
        //                  设置认证成功和认证失败处理器
        http.formLogin().successHandler(myAuthecationHandler).failureHandler(myAuthecationHandler);
        //配置退出登录处理器
        http.logout().logoutSuccessHandler(securityLogOutHandler);
        //配置没有权限处理器
        http.exceptionHandling().accessDeniedHandler(secutiryNotAccessHandler);
        return http.build();
    }

    /**
     * 自定义用户必须配置密码加密器，
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




}


