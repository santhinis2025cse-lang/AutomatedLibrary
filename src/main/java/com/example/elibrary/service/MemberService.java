package com.example.elibrary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.elibrary.model.Member;
import com.example.elibrary.repository.MemberRepository;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member updateMember(Long id, Member updatedMember) {
        Member existing = memberRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setName(updatedMember.getName());
        existing.setEmail(updatedMember.getEmail());
        existing.setPhone(updatedMember.getPhone());
        existing.setMembershipStart(updatedMember.getMembershipStart());
        existing.setMembershipExpiry(updatedMember.getMembershipExpiry());
        existing.setStatus(updatedMember.getStatus());
        return memberRepository.save(existing);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
