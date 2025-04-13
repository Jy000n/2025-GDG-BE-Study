package com.example.todo_api.hw;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyControllerTest {

    @Autowired
    private MyController myController;

    @Test
    public void controllerTest(){
        myController.controllerMethod();
    }
}
