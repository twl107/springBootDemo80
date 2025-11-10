package com.example.demo80.controller;

import com.example.demo80.dto.MemberDto;
import com.example.demo80.entity.Member;
import com.example.demo80.service.MemberService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/memberLogin")
    public String memberLoginGet() {
        return "member/memberLogin";
    }



    @GetMapping("/memberJoin")
    public String memberJoinGet(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/memberJoin";
    }

    @PostMapping("/memberJoin")
    public String memberJoinPost(RedirectAttributes rttr,
                                 @Valid MemberDto dto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "member/memberJoin";
        }
        else {
            try {
                Member member = Member.dtoToEntity(dto, passwordEncoder);
                Member memberRes = memberService.saveMember(member);
                rttr.addFlashAttribute("message", "회원에 가입되었습니다.");
                return "redirect:/member/memberLogin";
            } catch (IllegalStateException e) {
                rttr.addFlashAttribute("message", "이미 가입된 회원입니다.");
                return "redirect:/member/memberJoin";
            }
        }
    }

}
