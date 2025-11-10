package com.example.demo80.repository;

import com.example.demo80.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {




}
