package com.registration.reg.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
