package com.pairing.puzzlegame.utils;

import com.pairing.puzzlegame.entities.User;
import com.pairing.puzzlegame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class CurrentUserUtils {

    @Autowired
    private UserRepository userRepository;

    public String getCurrentUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userRepository.findByUsername(username).getId();
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userRepository.findByUsername(username);
    }

    public static Optional<HttpServletRequest> getCurrentHttpRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest);
    }
}