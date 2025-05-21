package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import com.example.todo_api.member.MemberRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)  // 어플리케이션 실행 시 미리 준비된 8080 포트 사용하여 테스트
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private MemberRepository memberRepository;

    // 생성 테스트
    @Test
    @Transactional   // 모든 JPA 기능 사용 코드는 transaction 내에서 발생 필요
    @Rollback(false)    // 수동으로 롤백 꺼줌
    void todoSaveTest(){
        // 트랜잭션 시작
        Todo todo = new Todo("todo content", false, null);  // 투두 객체 생성
        todoRepository.save(todo);

        // 트랜잭션 종료 => 커밋
        // 에러 발생 시 자동으로 롤백

        Assertions.assertThat(todo.getId()).isNotNull();
        // 테스트 환경 기준, 에러가 발생하지 않아도 테스트가 끝나면 자동으로 롤백
    }
    // in memory database

    // 단건 조회 테스트
    @Test
    @Transactional
    void todoFindOneByIdTest(){
        // given (기본 테스트 환경)
        Todo todo = new Todo("todo content", false, null);      // todo 데이터 생성해서 넣어줌
        todoRepository.save(todo);

        // when (테스트할 행위)
        Todo findTodo = todoRepository.findById(todo.getId());

        // then (행위를 통해 나타나는/기대하는 결과)
        Assertions.assertThat(findTodo.getId()).isEqualTo(todo.getId());
    }

    // 다건 조회 테스트
    @Test
    @Transactional
    void todoFindAllTest(){
        Todo todo1 = new Todo("todo content1", false, null);
        Todo todo2 = new Todo("todo content2", false, null);
        Todo todo3 = new Todo("todo content3", false, null);
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);

        List<Todo> todoList = todoRepository.findAll();

        //  System.out.println(todoList);
        Assertions.assertThat(todoList).hasSize(3);
    }
    // 조건 조회 테스트
    @Test
    @Transactional
    void todoFindAllByMemberTest(){
        Member member1 = new Member("a@naver.com", "a1234");
        Member member2 = new Member("b@naver.com", "b1234");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Todo todo1 = new Todo("todo content1", false, member1);
        Todo todo2 = new Todo("todo content2", false, member1);
        Todo todo3 = new Todo("todo content3", false, member2);
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);

        List<Todo> member1TodoList = todoRepository.findAllByMember(member1);
        List<Todo> member2TodoList = todoRepository.findAllByMember(member2);

        Assertions.assertThat(member1TodoList).hasSize(2);
        Assertions.assertThat(member2TodoList).hasSize(1);
    }
    
    // 수정 테스트
    @Test
    @Transactional
    @Rollback(false)        // DB에서 데이터 변경 확인
    void todoUpdateTest(){
        /*데이터 생성*/
        Todo todo1 = new Todo("todo content1", false, null);    // content 수정이라 member 상관 X (null)
        todoRepository.save(todo1);

        todoRepository.flushAndClear(); // 영속성 컨텍스트 비움
        /*없어도 됨*/
        
        Todo findTodo1 = todoRepository.findById(todo1.getId());    // 조회
        findTodo1.updateContent("new Content"); // 수정
    }

    // 삭제 테스트
    @Test
    @Transactional
    @Rollback(false)
    void todoDeleteTest(){
        Todo todo1 = new Todo("todo content1", false, null);
        Todo todo2 = new Todo("todo content2", false, null);
        todoRepository.save(todo1);
        todoRepository.save(todo2);

        todoRepository.flushAndClear();

        todoRepository.deleteById(todo1.getId());
    }

    @AfterAll       // 모든 테스트 끝났을 때
    public static void doNotFinish(){    // 메소드 실행
        System.out.println("test finished");
        while(true){}        // 무한 반복 -> 프로그램 실행 종료 X
    }


}
