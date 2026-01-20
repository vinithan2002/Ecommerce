package com.ecommerce.online.config;

import com.ecommerce.online.filter.JWTAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final JWTAuthFilter jwtAuthFilter;
    public SecurityConfig(JWTAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        SecurityContextRepository securityContextRepository =
                new RequestAttributeSecurityContextRepository();
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .anonymous(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                          .requestMatchers(HttpMethod.POST,"/authenticate").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/allProducts/**").hasAuthority(Permissions.PRODUCT_READ.name())
//                        .requestMatchers(HttpMethod.GET, "/products/**").hasAuthority(Permissions.PRODUCT_READ.name())
//                        .requestMatchers(HttpMethod.POST,"/products/**").hasAuthority(Permissions.PRODUCT_WRITE.name())
//                        .requestMatchers(HttpMethod.PUT,"/products/**").hasAuthority(Permissions.PRODUCT_UPDATE.name())
//                        .requestMatchers(HttpMethod.DELETE,"/products/**").hasAuthority(Permissions.PRODUCT_DELETE.name())
                        .anyRequest().authenticated());

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }
}
