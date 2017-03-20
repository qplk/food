package com.registration.reg.service;

import com.registration.reg.model.User;
import com.registration.reg.repository.RoleRepository;
import com.registration.reg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User get(Long userId) {
        return userRepository.getOne(userId);
    }

    @Override
    public void delete(Long userId) {
        userRepository.delete(userId);
    }

    @Override
    public void updateUser(Long id, User user) {
        User oldUser = this.get(id);

        if (user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }
        if (user.getGender() != null) {
            oldUser.setGender(user.getGender());
        }
        if (user.getInformation() != null) {
            oldUser.setInformation(user.getInformation());
        }
        if (user.getPhoneNumber() != null) {
            oldUser.setPhoneNumber(user.getPhoneNumber());
        }
        if (user.getUsername() != null) {
            oldUser.setUsername(user.getUsername());
        }


        this.save(oldUser);
    }
}
