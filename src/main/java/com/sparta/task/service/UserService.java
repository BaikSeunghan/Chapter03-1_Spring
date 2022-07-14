package com.sparta.task.service;

import com.sparta.task.domain.User;
import com.sparta.task.domain.UserRoleEnum;
import com.sparta.task.dto.SignupRequestDto;
import com.sparta.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        Optional<User> foundUser = userRepository.findByUsername(requestDto.getUsername());
        if (foundUser.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다");
        }

        String username = requestDto.getUsername();
        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        UserRoleEnum role = UserRoleEnum.USER;

        User user = new User(username, password, role);
        userRepository.save(user);
    }
}
