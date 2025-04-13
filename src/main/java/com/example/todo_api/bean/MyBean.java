package com.example.todo_api.bean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class MyBean {

    private final MySubBean mySubBean;

}
