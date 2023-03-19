package com.pyo.jwt.config.auth;

import com.pyo.jwt.model.User;
import com.pyo.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("프린시팔디테일서비스의 로드유저바이네임() : ");
        User userEntity = userRepository.findByUsername(username);
        System.out.println("유저엔티티   :  " + userEntity);
        return new PrincipalDetails(userEntity);
    }
}
