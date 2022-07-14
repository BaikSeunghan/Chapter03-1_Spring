package com.sparta.task.controller;


import com.sparta.task.dto.SignupRequestDto;
import com.sparta.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.util.StringUtils;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(Model model) {
        model.addAttribute("signupRequestDto", new SignupRequestDto());
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Validated @ModelAttribute("signupRequestDto") SignupRequestDto requestDto,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        } else if (!StringUtils.equals(requestDto.getPassword(), requestDto.getCheckPwd())) {
            bindingResult.reject("mismatchedPassword", "패스워드가 일치하지 않습니다.");
            return "signup";
        }
        if (requestDto.getPassword().indexOf(requestDto.getUsername()) != -1) {
            bindingResult.reject("nameIncludedPassword", "패스워드는 닉네임 포함 불가합니다.");
            return "signup";

        }
        try {
            userService.registerUser(requestDto);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            bindingResult.reject("alreadyMember", e.getMessage());
            return "signup";
        }
        return "redirect:/user/loginView";
    }
}
