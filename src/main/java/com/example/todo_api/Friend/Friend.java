package com.example.todo_api.Friend;

import com.example.todo_api.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private long id;

    // 팔로잉 유저
    @JoinColumn(name = "following_member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member followingMember;

    // 팔로워 유저
    @JoinColumn(name = "follower_member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member followerMember;

    public Friend(Member followingMember, Member followerMember) {
        this.followingMember = followingMember;
        this.followerMember = followerMember;
    }
}
