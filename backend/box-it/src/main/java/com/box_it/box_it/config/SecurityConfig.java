package com.box_it.box_it.config;

import jakarta.servlet.DispatcherType;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final DataSource dataSource;
//    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
//        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/user/signup"))
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/", "/api/user/signup", "/api/user/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginProcessingUrl("/api/user/login")
                        .defaultSuccessUrl("/", false)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/api/user/logout")
                        .logoutSuccessUrl("/api/user/login")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        manager.setUsersByUsernameQuery("SELECT member_id, password, enabled FROM member WHERE member_id = ?");

        // 권한이 필요하면 사용
        manager.setAuthoritiesByUsernameQuery("SELECT member_id, role FROM member_roles WHERE member_id = ?");

        return manager;
    }

}
