package com.multiauth.multiauthapplication.config.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

//    @Autowired
//    private Environment environment;
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true);
//        String[] allowedOrigins = environment.getProperty("allowed.origin").split(",");
//        for (String allowedOrigin : allowedOrigins) {
//            configuration.addAllowedOriginPattern(allowedOrigin.trim());
//        }
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }
//
//    @Bean
//    public FilterRegistrationBean<CorsFilter> corsFilter() {
//        final CorsConfigurationSource corsConfigurationSource = corsConfigurationSource();
//        final CorsFilter filter = new CorsFilter(corsConfigurationSource);
//
//        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(filter);
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return bean;
//    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedOrigins("*");
            }
        };
    }
}