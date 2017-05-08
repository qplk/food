package com.registration.reg.service.captcha;

/**
 * Created by Stasia on 08.05.17.
 */
public interface RecaptchaService {
    boolean isResponseValid(String remoteIp, String response);
}

