package com.haoshe.community.controller;

import com.haoshe.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Alpha") // give a name to the class, so it can handle requests to /Alpha
public class AlphaController {
    /*
    Controller handles HTTP requests, the service layer handles business logic.
    Controllers call this service, and the service accesses the DAO layer.
    Therefor, we inject AlphaService here to perform service operation
    The getData() method demonstrates how the controller interacts with the service layer,
    which in turn calls the find() method to retrieve data from the DAO layer
    */
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/Hello") // give a name to the method, so it can handle requests to /Alpha/Hello
    @ResponseBody                //  we only want server to return a String. Without the annotation, it will return a webpage.
    public String sayHello(){
        return "Hello, Spring boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }
}
