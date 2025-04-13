package com.example.todo_api.bean;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanTest {

    ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);  // context 생성

    @Test
    public void getAllBeanTest(){
        // 스프링 컨테이너를 설정 파일 정보를 이용해서 생성하고, 스프링 컨테이너 안에 있는 모든 빈을 조회하는 테스트
        for(String name : context.getBeanDefinitionNames()){
            System.out.println(name);
        };

        // Context 안에 MyBean 유무 검증 (코드로 자동화)
        Assertions.assertThat(context.getBeanDefinitionNames()).contains("myBean");
    }

    @Test
    // 스프링 컨테이너에 들어 있는 객체는 하나
    public void getOneBeanTest(){
        MyBean myBean1 = context.getBean(MyBean.class);  // .class -> 스프링 객체 타입으로 가져옴
        MyBean myBean2 = context.getBean(MyBean.class);
//        MyBean myBean3 = new MyBean();

        System.out.println(myBean1);    // 고유값 동일
        System.out.println(myBean2);    // 고유값 동일
//        System.out.println(myBean3);    // 다름

        Assertions.assertThat(myBean1).isSameAs(myBean2);
    }

    @Test
    public void dependencyInjection(){
        MyBean myBean = context.getBean(MyBean.class);
        MySubBean mySubBean = context.getBean(MySubBean.class);

        System.out.println(myBean.getMySubBean());
        System.out.println(mySubBean);

        Assertions.assertThat(myBean.getMySubBean()).isSameAs(mySubBean);
    }
}

