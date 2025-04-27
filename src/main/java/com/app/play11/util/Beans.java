package com.app.play11.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class Beans {

    @Bean
    Gson gson() {
        return new Gson();
    }

}
