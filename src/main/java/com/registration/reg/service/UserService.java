package com.registration.reg.service;

import com.registration.reg.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findByEmail(String email);
}
