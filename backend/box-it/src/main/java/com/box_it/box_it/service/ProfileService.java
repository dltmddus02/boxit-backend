package com.box_it.box_it.service;

import com.box_it.box_it.domain.Member;
import com.box_it.box_it.dto.ProfileResponse;
import com.box_it.box_it.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final MemberRepository memberRepository;

    @Autowired
    public ProfileService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public ProfileResponse edit (Long id, String nickname, String intro) {

        Member member = memberRepository.findById(id)
                .orElseThrow(IllegalStateException::new);

        member.setNickname(nickname);
        member.setIntro(intro);
        memberRepository.save(member);

        ProfileResponse profile = new ProfileResponse();
        profile.setNickname(nickname);
        profile.setIntro(intro);

        return profile;
    }

}
