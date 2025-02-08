package com.box_it.box_it.service;

import com.box_it.box_it.domain.Member;
import com.box_it.box_it.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //    회원가입
//    public Long join(Member member) {
//        validateDuplicateMember(member);
//
//        memberRepository.save(member);
//        return member.getId();
//    }
    public Long join(String username, String password) {
        Member member = new Member();
        member.setName(username);

        String encodedPassword = passwordEncoder.encode(password);
        member.setPassword(encodedPassword);

        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

}
