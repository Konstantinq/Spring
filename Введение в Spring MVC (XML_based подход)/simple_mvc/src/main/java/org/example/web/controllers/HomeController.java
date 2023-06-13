package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final Logger logger = Logger.getLogger(HomeController.class);
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(){
        logger.info("GEt /home return  index.html");
        return new ModelAndView("index");
    }
}
