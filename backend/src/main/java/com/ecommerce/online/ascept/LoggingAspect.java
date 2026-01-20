package com.ecommerce.online.ascept;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

//    @Before("execution(* com.ecommerce.online.Service.product.ProductServiceImpl.getProductById(..))")
//    public void beforelog()
//    {
//        System.out.println("Aspect log created before");
//    }

    @After("execution(* com.ecommerce.online.Service.product.ProductServiceImpl.getProductById(..))")
    public void afterlog()
    {
        System.out.println("Aspect log created after");
    }

//    @Around("execution(* com.ecommerce.online.Service.product.ProductServiceImpl.getProductById(..))")
//    public void aroundLog()
//    {
//        System.out.println("Aspect log Around");
//    }
}
