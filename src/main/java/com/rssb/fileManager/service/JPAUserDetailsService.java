package com.rssb.fileManager.service;

import com.rssb.fileManager.security.MyUserDetails;
import com.rssb.fileManager.model.User;
import com.rssb.fileManager.repo.UserAuthRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    UserAuthRepo userRepository;

    Logger logger = LoggerFactory.getLogger(JPAUserDetailsService.class);


    @Autowired
    private UserAuthRepo userAuthRepo;

    public void createUser (User userAuth) throws Exception {
        Optional<User> user = userAuthRepo.findByUsername(userAuth.getUsername());
        if (user.isPresent()){
            throw new RuntimeException("User already Exist");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userAuth.getPassword());
        userAuth.setPassword(encodedPassword);
        userAuthRepo.save(userAuth);
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        return user.map(MyUserDetails::new).get();
    }

}
