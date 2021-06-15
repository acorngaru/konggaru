package com.acorngaru.konggaru.config;

import com.acorngaru.konggaru.security.MemberAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // antmatcher를 통해 url 권한 획득 가능 추후 수정 예정
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/dashboard**", "/checkout**").hasRole("MEMBER")
                .antMatchers("/ingredient**").hasRole("ADMIN")
                .antMatchers( "/login**", "/login/register**", "/", "/product/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http.exceptionHandling().accessDeniedPage("/WEB-INF/views/error/403.jsp");
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        // antMatchers
        web.ignoring().antMatchers("/assets/**");
        web.ignoring().antMatchers("/favicon.ico");
        web.ignoring().antMatchers("/css/**","/image/**", "/js/**", "/font/**");
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new MemberAuthenticationProvider();
    }
}