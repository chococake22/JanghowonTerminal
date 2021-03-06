package janghowon.terminal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 보안 필터 적용이 필요없는 리소스 설정
    // css, js, image image 등은 필요가 없다.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/favicon.ico","/css/**", "/images/**",  "/js/**", // -- Static resources
                        "/css/**", "/images/**", "/js/**"
                        // -- Swagger UI v2
                        , "/v2/api-docs", "/swagger-resources/**"
                        , "/swagger-ui.html", "/webjars/**", "/swagger/**"
                        // -- Swagger UI v3 (Open API)
                        , "/v3/api-docs/**", "/swagger-ui/**", "/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/notice", "/write/{id}", "/location", "/board/search", "/searchtime", "/signup", "/login",
                        "/api/**", "/swagger-ui.html").permitAll()
                .antMatchers("/write").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/time/timeadd").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("user")
                .passwordParameter("1234")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error")
                .and()
                // csrf 설정 해제
                .csrf().disable();

    }
}
