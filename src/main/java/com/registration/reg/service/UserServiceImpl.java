package com.registration.reg.service;

import com.registration.reg.model.Order;
import com.registration.reg.model.Role;
import com.registration.reg.model.User;
import com.registration.reg.repository.OrderRepository;
import com.registration.reg.repository.RoleRepository;
import com.registration.reg.repository.UserRepository;
import com.registration.reg.requestBody.UserRequestBody;
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
    private OrderRepository orderRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(UserRequestBody userRequestBody) {
        User user = new User();

        user.setUsername(userRequestBody.getUsername());
        user.setPhoneNumber(userRequestBody.getPhoneNumber());
        user.setEmail(userRequestBody.getEmail());
        user.setInformation(userRequestBody.getInformation());
        user.setGender(userRequestBody.getGender());

        user.setPassword(bCryptPasswordEncoder.encode(userRequestBody.getPassword()));
        //user.setRoles(new HashSet<>(roleRepository.findAll()));
        user.getRoles().add(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);

        Order order = new Order("Initial order", user);
        orderRepository.save(order);

        user.getOrders().add(order);
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
    public void update(UserRequestBody user) {
        User oldUser = userRepository.getOne(user.getUserId());

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


        if (user.getRoles() != null) {
            oldUser.setRoles(user.getRoles());
        } else {
            oldUser.setRoles(new HashSet<Role>());
        }

        if (user.getPassword() != null) {
            oldUser.setPassword(bCryptPasswordEncoder.encode(user.getNewPasswordConfirm()));
        }


        userRepository.save(oldUser);
    }
}
