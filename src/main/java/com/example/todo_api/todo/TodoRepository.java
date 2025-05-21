package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository     // 레포지토리로 동작하는 빈
public class TodoRepository {

    @PersistenceContext       // 엔티티 매니저 객체 받아옴
    private EntityManager em;

    // 생성
    public void save(Todo todo){
        em.persist(todo);
    }

    // 조회
    // 단건 조회 (데이터 1개 조회)
    public Todo findById(Long todoId){     // PK = 데이터 조회 기준
        return em.find(Todo.class, todoId);      // find(어디서 가져올 건지 = 엔티티 클래스, 가져오려고 하는 데이터의 PK 값)
    }
    // 다건 조회 (여러 개의 데이터 조회)
    public List<Todo> findAll() {      // 컬렉션 (여러 개의 투두 data가 리스트에 들어감)
        return em.createQuery("select t from Todo as t", Todo.class).getResultList();     // 쿼리 작성 필요 (jpql문 쿼리, 투두 객체에 대한 결과)
    }
    // 조건 조회 (여러 개의 데이터 중에 조건이 있는 데이터만 조회)
    public List<Todo> findAllByMember(Member member){
        return em.createQuery("select t from Todo as t where t.member = :todo_member", Todo.class)
                .setParameter("todo_member", member)        // todo_member 파라미터에 메서드에서 받아온 member 넘기겠다
                .getResultList();
    }

    // 수정 - 엔티티 클래스의 필드를 수정하는 메서드 작성해서 수정

    // 삭제
    public void deleteById(Long todoId){
        Todo todo = findById(todoId);
        em.remove(todo);
    }

    // TEST 용도
    public void flushAndClear(){
        em.flush();     // 누적 사항 반영
        em.clear();     // 반영했으니까 영속성 컨텍스트 비우기
    }
}
