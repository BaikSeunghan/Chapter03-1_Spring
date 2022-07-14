package com.sparta.task.controller;

import com.sparta.task.domain.User;
import com.sparta.task.domain.UserRoleEnum;
import com.sparta.task.repository.UserRepository;
import com.sparta.task.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        System.out.println(userDetails.getUsername()); NullPointerException: null !!!
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());

            if (userDetails.getUser().getRole() == UserRoleEnum.ADMIN) {
                model.addAttribute("admin_role", true);
            }
        }

//        User user = userRepository.findByUsername(userDetails.getUsername())
//                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + userDetails.getUsername()));
//        model.addAttribute("username", user.getUsername());
        return "index";
    }
}
