package com.danal.chatting.entity.member;

import com.danal.chatting.Role.Role;
import com.danal.chatting.entity.Authority;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(
        uniqueConstraints={
                @UniqueConstraint(columnNames = "email"),
        }
)
public class Member {
    @Id
    @GeneratedValue
    Long userId;


    String email;

    String name;

    String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role")
    Authority authority;

    public static Member createMember(String email,String name,String password,Authority authority) {
        Member member = new Member();
        member.setEmail(email);
        member.setName(name);
        member.setPassword(password);
        member.setAuthority(authority);
        return member;
    }
}
