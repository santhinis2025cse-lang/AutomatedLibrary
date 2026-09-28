package com.example.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elibrary.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
