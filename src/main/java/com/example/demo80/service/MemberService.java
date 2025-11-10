package com.example.demo80.service;

import com.example.demo80.entity.Member;
import com.example.demo80.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public Member saveMember(Member member) {

        return memberRepository.save(member);
    }
}
