package com.danal.chatting.entity;

import com.danal.chatting.Role.Role;
import com.danal.chatting.entity.member.Member;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Authority {
    @Id
    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "authority")
    private List<Member> members = new ArrayList<Member>();

    public static Authority createAuthority(Role role) {
        Authority authority = new Authority();
        authority.setRole(role);
        return authority;
    }
}
