package com.lms.security;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    @Autowired
     OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

             http
                     .csrf(csrf-> csrf.disable())
                     .cors(Customizer.withDefaults())

                     .authorizeHttpRequests(auth-> auth
                             .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                             .requestMatchers(HttpMethod.POST,"/user/**").permitAll()
                             .requestMatchers(HttpMethod.POST,"/address").permitAll()

                             //books
                             .requestMatchers(HttpMethod.GET,"/book/**").authenticated()
                             .requestMatchers(HttpMethod.POST,"/book/**").hasRole("ADMIN")
                             .requestMatchers(HttpMethod.PUT, "/book/**").hasRole("ADMIN")
                             .requestMatchers(HttpMethod.PATCH, "/book/**").hasRole("ADMIN")
                             .requestMatchers(HttpMethod.DELETE, "/book/**").hasRole("ADMIN")

                             //library
                             .requestMatchers(HttpMethod.GET, "/library/**").authenticated()
                             .requestMatchers(HttpMethod.POST, "/library/**").hasRole("ADMIN")
                             .requestMatchers(HttpMethod.PUT, "/library/**").hasRole("ADMIN")
                             .requestMatchers(HttpMethod.PATCH, "/library/**").hasRole("ADMIN")
                             .requestMatchers(HttpMethod.DELETE, "/library/**").hasRole("ADMIN")
                             .requestMatchers("/oauth2/**","/login/oauth2/**").permitAll()

                             .anyRequest()
                             .authenticated())
                     .exceptionHandling(ex -> ex
                             .authenticationEntryPoint((request, response, authException) ->
                                     response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")))
                     .oauth2Login(auth->auth.successHandler(oAuth2LoginSuccessHandler))

                     .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
@Bean
    public AuthenticationManager authenticationManager() throws Exception
{
    return authenticationConfiguration.getAuthenticationManager();
}



    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                "http://localhost:5173",
                "https://library-management-frontend-pi-rouge.vercel.app"
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
