package com.project.dps.controller;

import com.project.dps.controller.dto.MemberDto;
import com.project.dps.domain.Member;
import com.project.dps.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    //TODO:: Session

    private final MemberService memberService;
    private int pageSize = 10;

    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/login";
    }

    @PostMapping("/login")
    public String login(Model model) {
        return "";
    }

    // 회원가입 페이지
    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@Valid MemberDto memberDto, BindingResult result) {

        if (result.hasErrors()) {
            System.out.printf("에러에러");
            return "member/join";
        }

        Member member = Member.builder()
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .password(memberDto.getPassword())
                .build();

        memberService.join(member);

        return "member/join-success";
    }

    // 로그아웃 페이지
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        return "";
    }


    // 회원 목록 페이지
    @GetMapping("/list")
    public String listMember(@PageableDefault Pageable pageable, Model model) {
        Page<Member> memberList = memberService.findMembers(pageable);
        System.out.println("totalPages: " + memberList.getTotalPages());
        model.addAttribute("memberList", memberList);

        return "member/memberList";
    }

    // 회원 검색


    // 회원 조회 페이지
    @GetMapping("/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        return "";
    }


}