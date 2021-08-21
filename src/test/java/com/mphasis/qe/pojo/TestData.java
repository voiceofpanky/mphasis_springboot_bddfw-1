package com.mphasis.qe.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:testData/userInfo.properties")
public class TestData {

    @Value( "${username}" )
    private String userName;
    @Value( "${password}" )
    private String password;

}
