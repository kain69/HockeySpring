package ru.karmazin.lab1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import ru.karmazin.lab1.service.PersonDetailsService;

import javax.sql.DataSource;

/**
 * @author Vladislav Karmazin
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailsService personDetailsService;

    private final DataSource dataSource;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService, DataSource dataSource) {
        this.personDetailsService = personDetailsService;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // конфигурируем сам Spring Security
        // конфигурируем авторизацию

        http.authorizeRequests()
                .antMatchers("/resources/**", "/static/**",
                        "/css/**", "/js/**", "/img/**", "/icon/**",
                        "/auth/login", "/auth/registration", "/error").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().hasAnyRole("USER","ADMIN")

                .and()
                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/auth/login?error=true")

                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())

                .and()
                .logout()
                .deleteCookies("JSESSIONID", "remember-me")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login")
        ;
    }

    // Настраиваем аутентификацию
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepo = new JdbcTokenRepositoryImpl();
        tokenRepo.setDataSource(dataSource);
        return tokenRepo;
    }
}
