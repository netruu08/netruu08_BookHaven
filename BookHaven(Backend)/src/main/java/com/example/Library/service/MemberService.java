package com.example.Library.service;


import com.example.Library.exception.ResourceNotFoundException;
import com.example.Library.model.Member;
import com.example.Library.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
    }

    public Member updateMember(Long id, Member memberDetails) {
        Member member = getMemberById(id);
        member.setFirstName(memberDetails.getFirstName());
        member.setLastName(memberDetails.getLastName());
        member.setEmail(memberDetails.getEmail());
        member.setPhoneNumber(memberDetails.getPhoneNumber());
        member.setActive(memberDetails.isActive());
        member.setCurrentBorrows(memberDetails.getCurrentBorrows());
        return memberRepository.save(member);
    }
}