package com.example.todo_api.todo;

import com.example.todo_api.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter         // 엔티티 조회 시, 엔티티 필드 다 읽어 볼 수 있음 (private이니까 필요..?)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id                 // DB에서 PK로 사용 (기본키) 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // 생성된 값 넣어줌 (ID 자동 생성값)
    @Column(name = "todo_id")
    private long id;    // 데이터 식별 위한 PK

    @Column(name = "todo_content", columnDefinition = "varchar(200)")
    private String content;

    @Column(name = "todo_is_checked", columnDefinition = "tinyInt(1)")
    private boolean isChecked;

    // 외래키 컬럼
    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Todo(String content, boolean isChecked, Member member) {
        this.content = content;
        this.isChecked = isChecked;
        this.member = member;
    }

//    @Builder
//    private Todo(String content, boolean isChecked, Member member) {
//        this.content = content;
//        this.isChecked = isChecked;
//        this.member = member;
//    }
}
