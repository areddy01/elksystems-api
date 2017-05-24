package com.elksystems.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.elksystems.rest.user.service" })
public class ServiceConfig {
}
