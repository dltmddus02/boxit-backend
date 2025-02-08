package com.box_it.box_it.controller;

import com.box_it.box_it.domain.Member;
import com.box_it.box_it.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class MemberController {
    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberController(MemberService memberService, AuthenticationManager authenticationManager,
                            @Qualifier("userDetailsService") UserDetailsService userDetailsService,
                            PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody Member member) {
        Long memberId = memberService.join(member.getName(), member.getPassword());
        return ResponseEntity.ok(memberId);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member member) {
        String username = member.getName();
        String password = member.getPassword();
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println("유저 조회 성공: " + userDetails.getUsername());

            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 비밀번호 불일치");
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok().body("로그인 성공: " + username);
        } catch (Exception e) {
            System.err.println("로그인 오류: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그인 중 내부 오류 발생");
        }
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 비밀번호가 일치하지 않습니다.");
//        }
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password)
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        return ResponseEntity.ok().body("로그인 성공" + username);
    }
}
