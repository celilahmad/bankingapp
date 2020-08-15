package app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**",
                        "/", "/detail/**", "/search")
                .permitAll()
                //.anyRequest().authenticated()
                .and()

                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .successForwardUrl("/")
                .failureUrl("/login?error")

                .and()
                .logout()
                .deleteCookies("JSESSIONID")

                .and()
                .rememberMe().
                key("uniqueAndSecret").rememberMeParameter("remember-me")

                .and()
                .csrf().disable();
    }
    @Override
    public void configure(WebSecurity web){
        web
                .ignoring()
                .antMatchers("/resources/**");

    }

}
