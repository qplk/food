package com.registration.reg.service;

import com.registration.reg.model.User;
import com.registration.reg.requestBody.UserRequestBody;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAll();

    User get(Long userId);

    void delete(Long userId);

    void update(Long id, UserRequestBody user);
}
