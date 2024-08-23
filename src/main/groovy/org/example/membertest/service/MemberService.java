package org.example.membertest.service;

import lombok.RequiredArgsConstructor;
import org.example.membertest.dto.*;
import org.example.membertest.dto.MemberSimpleResponseDto;
import org.example.membertest.entity.Member;
import org.example.membertest.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional //해당 메서드 내에서 실행되는 모든 데이터베이스 작업이 하나의 트랜잭션으로 관리
    public MemberSaveResponseDto saveMember(MemberSaveRequestDto requestDto) {
        Member newMember = new Member(requestDto.getName());
        Member savedMember = memberRepository.save(newMember);

        return new MemberSaveResponseDto(savedMember.getId(), savedMember.getName());
    }

    public List<MemberSimpleResponseDto> getMembers() {
        List<Member> memberList = memberRepository.findAll();

        List<MemberSimpleResponseDto> dtoList = new ArrayList<>();
        for (Member member : memberList) {
            MemberSimpleResponseDto dto = new MemberSimpleResponseDto(member.getName());
            dtoList.add(dto);
        }

        return dtoList;
    }

    public MemberDetailResponseDto getMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NullPointerException("멤버가 없습니다."));

        return new MemberDetailResponseDto(member.getId(), member.getName());
    }

    @Transactional
    public MemberUpdateResponseDto updateMember(Long memberId, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NullPointerException("멤버가 없습니다."));

        member.update(requestDto.getName());

        return new MemberUpdateResponseDto(member.getId(), member.getName());
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NullPointerException("멤버가 없습니다."));

        memberRepository.delete(member);
    }
}
