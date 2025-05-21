package com.example.todo_api.member;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    void memberSaveTest(){
        Member member = new Member("a@naver.com", "a1234");
        memberRepository.save(member);

        Assertions.assertThat(member.getId()).isNotNull();
    }

    @Test
    @Transactional
    void memberFindOneByIdTest(){
        Member member = new Member("a@naver.com", "a1234");
        memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId());

        Assertions.assertThat(findMember).isNotNull();
        Assertions.assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
    }

    @Test
    @Transactional
    void memberFindAllTest(){
        Member member1 = new Member("a@naver.com", "a1234");
        Member member2 = new Member("b@naver.com", "b1234");
        Member member3 = new Member("c@naver.com", "c1234");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        List<Member> memberList = memberRepository.findAll();

        Assertions.assertThat(memberList).hasSizeGreaterThanOrEqualTo(3);
    }

    @Test
    @Transactional
    void memberFindAllByEmailTest(){
        Member member1 = new Member("a@naver.com", "a1234");
        Member member2 = new Member("b@naver.com", "b1234");
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> emailAList = memberRepository.findByEmail("a@naver.com");
        List<Member> emailBList = memberRepository.findByEmail("b@naver.com");

        Assertions.assertThat(emailAList).hasSize(1);
        Assertions.assertThat(emailBList).hasSize(1);
    }

    @Test
    @Transactional
    void memberUpdateTest(){
        Member member = new Member("a@naver.com", "a1234");
        memberRepository.save(member);

        memberRepository.flushAndClear();

        Member findMember1 = memberRepository.findById(member.getId());
        findMember1.updateEmail("update@naver.com");
    }

    @Test
    @Transactional
    @Rollback(false)
    void memberDeleteTest(){
        Member member1 = new Member("a@naver.com", "a1234");
        Member member2 = new Member("b@naver.com", "b1234");
        memberRepository.save(member1);
        memberRepository.save(member2);

        memberRepository.flushAndClear();

        memberRepository.deleteById(member1.getId());
    }

    @AfterAll
    public static void doNotFinish(){
        System.out.println("test finished");
        while(true){}
    }
}
