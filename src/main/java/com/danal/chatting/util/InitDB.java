package com.danal.chatting.util;

import com.danal.chatting.Role.Role;
import com.danal.chatting.entity.Authority;
import com.danal.chatting.entity.member.Member;
import com.danal.chatting.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
//        initService.dbInit2();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        private final MemberService memberService;
        private final PasswordEncoder passwordEncoder;

        // 메소드 분리
        private void initMember() {
            Member admin = Member.createMember("admin@admin.com","admin",passwordEncoder.encode("admin"), Authority.createAuthority(Role.ROLE_ADMIN));
            Member manager = Member.createMember("manager@manager.com","manager",passwordEncoder.encode("manager"), Authority.createAuthority(Role.ROLE_MANAGER));
            Member user = Member.createMember("user@user.com","user",passwordEncoder.encode("user"), Authority.createAuthority(Role.ROLE_USER));
            em.persist(admin);
            em.persist(manager);
            em.persist(user);
        }

        public void dbInit() {
            initAuthority();
            initMember();
        }

        private void initAuthority() {
            Authority role_admin = Authority.createAuthority(Role.ROLE_ADMIN);
            Authority role_manager = Authority.createAuthority(Role.ROLE_MANAGER);
            Authority role_user = Authority.createAuthority(Role.ROLE_USER);

            em.persist(role_admin);
            em.persist(role_manager);
            em.persist(role_user);
            em.flush();
        }
    }
    }

