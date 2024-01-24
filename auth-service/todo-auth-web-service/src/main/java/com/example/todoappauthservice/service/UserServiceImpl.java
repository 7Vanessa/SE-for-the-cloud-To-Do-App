package com.example.todoappauthservice.service;

import com.example.todoappauthservice.entity.UserEntity;
import com.example.todoappauthservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserEntity createUser(UserEntity user){
        log.debug("Creating user: {}", user.getUsername());
        log.debug("User details before save: {}", user);

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles("USER");
        }

        user.setCreatedAt(new Date());

        try{
            log.debug("Attempting to register user: {}", user.getUsername());

            userRepository.save(user);
            log.info("User registered successfully: {}", user.getUsername());
            log.debug("User details after save: {}", user);
        } catch (DataIntegrityViolationException e){
            log.error("Error registering user", e);
            throw new RuntimeException("Error registering user", e);
        }
        return user;
    }

    @Override
    public Optional<UserEntity> getUserByUsername(String username){
        log.debug("Attempting to retrieve user by username: {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserEntity> getUserById(Long id){
        log.debug("Attempting to retrieve user by ID: {}", id);
        return userRepository.findById(id);
    }

    @Override
    public boolean isUsernameTaken(String username){
        log.debug("Checking if username is taken: {}", username);
        Optional<UserEntity> user = userRepository.findByUsername(username);
        return user.isPresent();
    }

    @Override
    public boolean isEmailTaken(String email){
        log.debug("Checking if email is taken: {}", email);
        Optional<UserEntity> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if (userEntityOptional.isEmpty()){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        UserEntity userEntity = userEntityOptional.get();

        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                null
        );

        return userDetails;
    }
}
