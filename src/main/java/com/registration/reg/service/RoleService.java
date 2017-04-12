package com.registration.reg.service;

import com.registration.reg.model.Role;

import java.util.List;

/**
 * Created by Stasia on 06.04.17.
 */
public interface RoleService {
    void save(Role role);

    Role get(Long roleId);

    List<Role> findAll();

    void delete(Long roleId);
}
