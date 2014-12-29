package com.proeza.sgs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.proeza", excludeFilters = {@Filter(Configuration.class)})
public class ContextConfig {

}