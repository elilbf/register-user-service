package com.gft.registeruserservice.util;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource("classpath:emaildomain.properties")
@Setter
public class EmailDomainProperty {

    @Value("${email.domain}")
    private String domains;

    public List<String> getDomains() {
        return Arrays.stream(domains.split(",")).toList();
    }

}
