package com.salimahafirassou.paymybuddy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     //return new BCryptPasswordEncoder();
    //     return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // with new spring security 5
    // }
}
