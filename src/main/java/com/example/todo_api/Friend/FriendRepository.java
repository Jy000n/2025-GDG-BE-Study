package com.example.todo_api.friend;

import com.example.todo_api.friend.Friend;
import com.example.todo_api.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendRepository {

    @PersistenceContext
    private EntityManager em;

    // 생성
    public void save(Friend friend){
        em.persist(friend);
    }

    // 단건 조회
    public Friend findById(Long friendId){
        return em.find(Friend.class, friendId);
    }
    // 다건 조회
    public List<Friend> findAll(){
        return em.createQuery("select f from Friend as f", Friend.class)
                .getResultList();
    }
    // 조건 조회 (내가 팔로잉한 대상 조회)
    public List<Friend> findAllByMember(Member member){
        return em.createQuery("select f from Friend as f where f.followerMember = :member", Friend.class)
                .setParameter("member", member)
                .getResultList();
    }

    // 삭제
    public void deleteById(Long friendId){
        Friend friend = findById(friendId);
        if(friend != null){
            em.remove(friend);
        }
    }

    // 테스트용
    public void flushAndClear(){
        em.flush();
        em.clear();
    }
}
