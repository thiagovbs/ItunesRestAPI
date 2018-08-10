package com.desafio.stefanini.itunesrestclient;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


public class ServletInitializer extends SpringBootServletInitializer
{

    public ServletInitializer()
    {
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(ItunesRestApiApplication.class);
    }
}
