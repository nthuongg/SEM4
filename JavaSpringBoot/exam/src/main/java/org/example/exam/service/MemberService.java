package org.example.exam.service;

import org.example.exam.entity.Member;
import org.example.exam.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Optional<Member> findByUserId(String userId) {
        return memberRepository.findById(userId);
    }
}