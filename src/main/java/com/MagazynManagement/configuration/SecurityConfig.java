package com.MagazynManagement.configuration;

import com.MagazynManagement.service.CustomSuccessHandler;
import com.MagazynManagement.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(c -> c.disable())

                .authorizeHttpRequests(request -> request.requestMatchers("/admin/**")
                        .hasAuthority("ADMIN").requestMatchers("/user/**").hasAuthority("USER")
                        .requestMatchers("/pracownik/**").hasAnyAuthority("PRACOWNIK")
                        .requestMatchers("/manager/**").hasAnyAuthority("MANAGER", "ADMIN")
                        .requestMatchers("/registration").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/placowki").permitAll()
                        .requestMatchers("/produkty").permitAll()
                        .requestMatchers("/stan-magazynu/**").permitAll()
                        .requestMatchers("/material/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
                        .successHandler(customSuccessHandler).permitAll())
                .logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout").permitAll());

        return http.build();
    }

    @Autowired
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
