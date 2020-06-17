package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Aspect
@Component
public class ServiceAspect {


    @Pointcut("execution(* com.example.demo.service..*.*(..))")
    public void service(){}


    @Before("service()")
    public void beforeService(JoinPoint joinPoint){
        System.out.println("Before Service");
    }


    @After("service()")
    public void afterService(){
        System.out.println("After Service");
    }

   @Around("service()")
   public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object=null;
        try{
            System.out.println("Before Around Service");
            object=joinPoint.proceed();
            System.out.println("After Around Service. "+object);
        }catch(Throwable throwable){
            throwable.printStackTrace();
        }
       return object;
    }
}