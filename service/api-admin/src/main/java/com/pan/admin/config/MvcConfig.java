// package com.pan.admin.config;
//
// import com.pan.admin.interceptor.LoginInterceptor;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
// import javax.annotation.Resource;
//
// /**
//  * @description:
//  * @author: Mr.Pan
//  * @create: 2023-02-21 22:23
//  **/
// @Configuration
// public class MvcConfig implements WebMvcConfigurer {
//     @Resource
//     private LoginInterceptor loginInterceptor;
//
//     @Override
//     public void addInterceptors(InterceptorRegistry registry) {
//         registry.addInterceptor(loginInterceptor)
//                 .addPathPatterns("/**")
//                 .excludePathPatterns(
//                         "/user/login",
//                         "/user/regist",
//                         "/user/user-by-ak",
//                         "/user/sk-by-ak",
//                         "/interface-info/url-method",
//                         "/interface-info/path-method",
//                         "/user-interface-info/check-invoke-auth",
//                         "/user-interface-info/count-increment"
//                 )/*放行swagger相关路径*/
//                 .excludePathPatterns(
//                         "/swagger-ui.html/**",
//                         "/swagger-resources/**",
//                         "/webjars/**", "/v2/**",
//                         "/doc.html/**")
//                 .order(0);
//
//     }
//
//     /*配置拦截器访问knif4j静态资源*/
//     @Override
//     public void addResourceHandlers(ResourceHandlerRegistry registry) {
//         //配置拦截器访问静态资源
//         registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
//         registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/META-INF/resources/");
//         registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//     }
// }
