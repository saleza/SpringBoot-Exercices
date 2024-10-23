package com.openclassroom.helloworld;

import com.openclassroom.helloworld.model.HelloWorld;
import org.springframework.stereotype.Component;

@Component
public class BusinessService {
    public HelloWorld getHelloWorld() {
        HelloWorld hw = new HelloWorld();
        return hw;
    }
}
