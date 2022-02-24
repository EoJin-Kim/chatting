package com.danal.chatting;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

@SpringBootApplication
public class ChattingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChattingApplication.class, args);
	}
	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
		return new JPAQueryFactory(entityManager);
	}
}
