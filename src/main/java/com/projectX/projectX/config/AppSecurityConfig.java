package com.projectX.projectX.config;

import com.projectX.projectX.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsServiceImpl userServiceImpl;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public AppSecurityConfig(UserDetailsServiceImpl userServiceImpl, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userServiceImpl = userServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public AuthenticationProvider authProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userServiceImpl);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .csrf()
                    .disable()
                    .headers()
                    .frameOptions()
                    .disable()
                    .and()
                    .authorizeRequests()
                    .antMatchers( "/", "/register", "/login")
                    .permitAll()
                    .antMatchers("/logout", "/logout-success")
                    .authenticated()
                    .antMatchers("/console", "/console/**")
                    .hasAnyAuthority("ADMIN")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .failureUrl("/login?error=true")
                    .defaultSuccessUrl("/")
                    .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/logout-success");
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/db/**", "/templates/**");
    }

}
