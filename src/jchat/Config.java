package jchat;

import jchat.service.UserRegisterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    UserRegisterService createUserManagerService(){
        return new UserRegisterService();
    }

}