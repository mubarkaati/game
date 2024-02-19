package com.pairing.puzzlegame.service.impl;

import com.pairing.puzzlegame.entities.User;
import com.pairing.puzzlegame.model.request.LoginRequestDto;
import com.pairing.puzzlegame.model.request.UserRegisterRequestDto;
import com.pairing.puzzlegame.model.response.LoginResponseDto;
import com.pairing.puzzlegame.model.response.RegisterUserResponseDto;
import com.pairing.puzzlegame.model.response.UserInfoResponseDto;
import com.pairing.puzzlegame.repository.UserRepository;
import com.pairing.puzzlegame.service.UserService;
import com.pairing.puzzlegame.utils.CurrentUserUtils;
import com.pairing.puzzlegame.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CurrentUserUtils currentUserUtils;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${role}")
    private String userRole;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.
                User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
        );
    }

    @Override
    public RegisterUserResponseDto registerUser(UserRegisterRequestDto registerRequestDto) {
        RegisterUserResponseDto registerUserResponseDto = new RegisterUserResponseDto();
        if (registerRequestDto.getUsername() != null && !StringUtils.isEmpty(registerRequestDto.getUsername())) {
            User user = userRepository.findByUsername(registerRequestDto.getUsername());
            if (user == null) {
                user = new User();
                user.setUsername(registerRequestDto.getUsername());
                user.setFullName(registerRequestDto.getFullName());
                user.setRole(userRole);
                user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
                user.setUpdatedBy(user.getId());
                userRepository.save(user);
                registerUserResponseDto.setId(user.getId());
                registerUserResponseDto.setMessage("USER_REGISTERED_SUCCESSFULLY");
            } else {
                registerUserResponseDto.setMessage("EMAIL_ALREADY_EXIST");
            }
            return registerUserResponseDto;
        }
        registerUserResponseDto.setMessage("USERNAME_CANNOT_BE_EMPTY");
        return registerUserResponseDto;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        if (loginRequestDto.getUsername() != null && loginRequestDto.getPassword() != null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
            LoginResponseDto loginResponseDto = new LoginResponseDto();
            User user = userRepository.findByUsername(loginRequestDto.getUsername());
            String jwtToken = jwtUtils.generateToken(new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))));
            loginResponseDto.setJwtToken(jwtToken);
            return loginResponseDto;
        } else {
            throw new BadCredentialsException("Username and Password is Null");
        }
    }

    @Override
    public List<UserInfoResponseDto> getAllUsers() {
        String currentUserId = currentUserUtils.getCurrentUserId();
        return userRepository.findAll().stream().filter(user -> !user.getId().equals(currentUserId)).map(this::mapUserToUserInfoDto).collect(Collectors.toList());
    }

    private UserInfoResponseDto mapUserToUserInfoDto(User user) {
        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto();
        userInfoResponseDto.setFullName(user.getFullName());
        userInfoResponseDto.setId(user.getId());
        return userInfoResponseDto;
    }
}