package com.example.todo_api.bean;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest     // 테스트 실행 전, 스프링부트 실행 -> 모든 스프링 빈을 컨테이너에 넣어서 올리고 테스트 실행
public class BeanTest2 {

    @Autowired
    private MyBean myBean;      // 여기가 통로야

    @Autowired
    private MySubBean mySubBean;    // 여기가 통로야

    @Test
    public void dependencyInjection(){
        System.out.println(myBean.getMySubBean());
        System.out.println(mySubBean);

        Assertions.assertThat(myBean.getMySubBean()).isSameAs(mySubBean);
    }
}
