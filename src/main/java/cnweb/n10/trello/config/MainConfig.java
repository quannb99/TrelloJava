package cnweb.n10.trello.config;

import cnweb.n10.trello.model.BoardValidate;
import cnweb.n10.trello.model.UserValidate;
import cnweb.n10.trello.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class MainConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/").permitAll();
        // Các trang chỉ truy cập được khi là khách
        http.authorizeRequests().antMatchers("/login").access("isAnonymous()");
        http.authorizeRequests().antMatchers("/board").access("hasAuthority('USER')");
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/index");
        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security") // Submit URL
                .loginPage("/login") // trang login
                .defaultSuccessUrl("/index") // login thành công
                .failureUrl("/login?error=true") // login thất bại
                .usernameParameter("username")
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/index");

    }
    @Bean
    UserValidate userValidate(){
        return new UserValidate();
    }
    @Bean
    BoardValidate boardValidate(){ return new BoardValidate();}
}

