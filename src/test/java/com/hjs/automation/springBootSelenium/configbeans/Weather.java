package com.hjs.automation.springBootSelenium.configbeans;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class Weather {
    @Value(value = "${weather.dt}")
    private String dt;

    @Value(value = "${weather.humidity}")
    private String humidity;

    @Value(value = "${weather.city}")
    private String city;

    @Value(value = "${weather.wd}")
    private Integer wd;
}
