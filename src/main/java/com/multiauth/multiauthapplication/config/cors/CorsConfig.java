package com.multiauth.multiauthapplication.config.cors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Autowired
    private Environment environment;

    /**
     * CorsConfigurationSource allows web servers to specify who can access its resources
     *
     * @return -Return registered configuration response
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        String[] allowedOrigins = environment.getProperty("allowed.origin").split(",");
        for (String allowedOrigin : allowedOrigins) {
            configuration.addAllowedOrigin(allowedOrigin.trim());
        }
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    /**
     * Configure a filter instance and define its order of execution in the filter chain
     *
     * @return -Return filtered bean responses
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        final CorsConfigurationSource corsConfigurationSource = corsConfigurationSource();
        final CorsFilter filter = new CorsFilter(corsConfigurationSource);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}