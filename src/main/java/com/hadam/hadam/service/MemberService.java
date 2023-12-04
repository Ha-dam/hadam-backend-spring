package com.hadam.hadam.service;

import com.hadam.hadam.domain.Member;
import com.hadam.hadam.dto.request.CreateMemberReq;
import com.hadam.hadam.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long createMember(CreateMemberReq createMemberReq){
        Member newMember = Member.builder()
                .createMemberReq(createMemberReq)
                .build();
        memberRepository.save(newMember);
        return newMember.getId();
    }

    @Transactional(readOnly = true)
    public Long checkMemberExist(String identifier){
        Member member = memberRepository.findByIdentifier(identifier);
        return (member != null) ? member.getId() : 0L;
    }



}
