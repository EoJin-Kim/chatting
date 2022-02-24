package com.danal.chatting.security.service;

import com.danal.chatting.entity.Authority;
import com.danal.chatting.entity.member.Member;
import com.danal.chatting.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userDetailsService")
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        Optional<Member> findMember = memberRepository.findMemberByEmail(username);
        return findMember
                .map(member -> createMember(username, member))
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createMember(String username, Member member) {

        // DB의 권한 정보 가지고 온다
//        List<GrantedAuthority> grantedAuthorities = member.getMemberAuthorities().stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getAuthorityName()))
//                .collect(Collectors.toList());
        Authority authority = member.getAuthority();
        String role = authority.getRole().toString();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));



        return new org.springframework.security.core.userdetails.User(member.getEmail(),
                member.getPassword(),
                authorities);
    }
}
