package com.haoshe.community.service;

import com.haoshe.community.dao.AlphaDao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
/*
A new bean instance is created every time you request it from the Spring container
Singleton(default) -> Only one shared bean instance per Spring container
we almost always use singleton, so no need to explicitly add @Scope("singleton")
 */
// @Scope("prototype")
@Lazy
public class AlphaService {
    /*
    Controller handles HTTP requests, the service layer handles business logic
    Controllers call this service, and the service accesses the DAO layer
    Therefor, we inject AlphaDao here to perform database operation
    The find() method demonstrates how the service layer interacts with the DAO layer.
     */
    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("AlphaService constructor called");
    }

    @PostConstruct // it will be called after the bean is created
    public void init(){
        System.out.println("AlphaService initialized");
    }

    @PreDestroy // call the method before destroying the bean is destroyed
    public void cleanupBeforeDestroy(){
        System.out.println("free resources before AlphaService destruction");
    }

    public String find(){
        return alphaDao.select();
    }
}
