package com.base;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;

@Configuration
@ComponentScan(basePackages = {"com.base"})
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, MalformedURLException {
        SpringApplication app = new SpringApplication(AppConfig.class);
//        app.setWebEnvironment(true);
        app.setBannerMode(Banner.Mode.OFF);
        Set<String> set = new HashSet<String>();
        app.setSources(set);
        app.run(args);
    }
}
