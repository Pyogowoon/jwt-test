package com.pyo.jwt.config;

import com.pyo.jwt.filter.MyFilter1;
import com.pyo.jwt.filter.MyFilter3;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;

    @Bean
    SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
        http.addFilterBefore(new MyFilter3(), BasicAuthenticationFilter.class);
        http.csrf().disable().
                /*JWT 방식 , Session을 사용하지 않고 Stateless 하게. 폼로그인 방식과 Http방식도 사용하지않을것 */
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter) // CrossOrigin(인증x), 시큐리티 필터에 등록 인증(o)
                .formLogin().disable()
                .httpBasic().disable()
                        /* */
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
        return http.build();
    }
}
