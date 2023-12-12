package com.kosvad9.service;

import com.kosvad9.database.entity.Staff;
import com.kosvad9.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(username).map(user -> {
            Set<GrantedAuthority> authorities;
            authorities = (user instanceof Staff) ? Collections.singleton(((Staff) user).getRole()):Collections.EMPTY_SET;
            return new User(
                    user.getId().toString(),
                    user.getPassword(),
                    authorities
            );
        }).orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден!"));
    }
}
