package com.box_it.box_it.controller;

import com.box_it.box_it.domain.Member;
import com.box_it.box_it.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Long> singUp(@RequestBody Member member) {
        Long memberId = memberService.join(member.getName(), member.getPassword());
        return ResponseEntity.ok(memberId);
    }
}
