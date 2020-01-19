//package com.negotium.negotiumapp.security.v2;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and()
//                .csrf().ignoringAntMatchers("/h2-console").disable() // protects from unauthorized access to H2 database
//                .authorizeRequests().antMatchers("/**/favicon.ico").permitAll()
//
//                .antMatchers(
//                        SecurityConstants.PUBLIC_URL,
//                        SecurityConstants.AUTH_LOGIN_URL,
//                        "/",
//                        "/register",
//                        "/loginform").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
//                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder().encode("password"))
//                .authorities("ROLE_USER");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /*
//    * Cross-origin resource sharing (CORS) is a mechanism that allows restricted resources on a web
//    * page to be requested from another domain outside the domain from which the first resource was served.
//    * A web page may freely embed cross-origin images, stylesheets, scripts, iframes, and videos.
//    * Certain "cross-domain" requests, notably Ajax requests, are forbidden by default by the same-origin security
//    * policy. CORS defines a way in which a browser and server can interact to determine whether it is safe to allow
//    * the cross-origin request.[3] It allows for more freedom and functionality than purely same-origin
//    * requests, but is more secure than simply allowing all cross-origin requests.
//    *
//    * https://en.wikipedia.org/wiki/Cross-origin_resource_sharing
//    * */
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//
//        return source;
//    }
//}
//
