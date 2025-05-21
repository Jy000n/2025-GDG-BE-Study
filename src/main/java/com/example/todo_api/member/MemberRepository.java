package com.example.todo_api.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 생성
    public void save(Member member) {
        em.persist(member);
    }

    // 조회
    // 단건 조회
    public Member findById(Long memberId){
        return em.find(Member.class, memberId);
    }
    // 다건 조회
    public List<Member> findAll(){
        return em.createQuery("select m from Member as m", Member.class).getResultList();
    }
    // 조건 조회 (이름으로 회원 검색)
    public List<Member> findByEmail(String email){
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }

    // 삭제
    public void deleteById(Long memberId){
        Member member = findById(memberId);
        if(member != null){
            em.remove(member);
        }
    }

    // 테스트용
    public void flushAndClear(){
        em.flush();
        em.clear();
    }
}
