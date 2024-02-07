package com.pan.common.config;

import com.pan.common.interceptor.GatewaySourceInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@AllArgsConstructor
public class CommonMvcConfig implements WebMvcConfigurer {
    private final GatewaySourceInterceptor gatewaySourceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(gatewaySourceInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns(
                "/swagger-ui.html/**",
                "/swagger-resources/**",
                "/webjars/**", 
                "/v3/**",
                "/doc.html/**"
            )
            .order(-99);

    }

    /*配置拦截器访问knif4j静态资源*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置拦截器访问静态资源
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
