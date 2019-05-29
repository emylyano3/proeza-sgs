package com.proeza.sgs.config.env;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
    Prod.class,
    Cloud.class,
    Dev.class,
    Test.class
})
public class Environments {

}