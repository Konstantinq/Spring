package org.example.app.services;


import org.example.web.dto.LoginForm;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;


@Service
public class LoginService {

    private final Logger logger = getLogger(LoginService.class);

    public boolean authenticate(LoginForm loginForm){
        logger.info("try auth with user-form: " + loginForm);
        return  loginForm.getUserName().equals("root") && loginForm.getPassword().equals("123");
    }
}
