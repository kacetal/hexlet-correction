package io.hexlet.hexletcorrection.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static io.hexlet.hexletcorrection.controller.ControllerConstants.ACCOUNTS_PATH;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.API_PATH_V1;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.CORRECTIONS_PATH;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.RESOURCES_PATH;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.SWAGGER_UI_PATH;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(ACCOUNTS_PATH, CORRECTIONS_PATH)
            .hasRole("USER")
            .antMatchers("/", "/**")
            .permitAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .and()
            .logout()
            .logoutSuccessUrl("/");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
////            .userDetailsService(userDetailsService)
////            .passwordEncoder(passwordEncoder());
//    }

    @Override
    public void configure(WebSecurity web) {
        web
            .ignoring()
            .antMatchers(RESOURCES_PATH + "/**",
                API_PATH_V1 + "/**",
                SWAGGER_UI_PATH + "/**",
                "/v2/api-docs/**",
                "/webjars/springfox-swagger-ui/**",
                "/swagger-resources/**",
                "/dist/**",
                "/public/**");
    }
}
