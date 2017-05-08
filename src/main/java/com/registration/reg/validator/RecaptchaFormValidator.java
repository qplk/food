package com.registration.reg.validator;

import com.registration.reg.requestBody.RecaptchaForm;
import com.registration.reg.service.captcha.exception.RecaptchaServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.WebApplicationContext;
import com.registration.reg.service.captcha.RecaptchaService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Stasia on 08.05.17.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RecaptchaFormValidator implements Validator {

    private static final String ERROR_RECAPTCHA_INVALID = "recaptcha.error.invalid";
    private static final String ERROR_RECAPTCHA_UNAVAILABLE = "recaptcha.error.unavailable";
    private final HttpServletRequest httpServletRequest;

    @Autowired
    private final RecaptchaService recaptchaService;

    @Autowired
    public RecaptchaFormValidator(HttpServletRequest httpServletRequest, RecaptchaService recaptchaService) {
        this.httpServletRequest = httpServletRequest;
        this.recaptchaService = recaptchaService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RecaptchaForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String recaptchaResponse = (String) target;
        try {
            if (!recaptchaService.isResponseValid(getRemoteIp(httpServletRequest), recaptchaResponse)) {
                errors.reject(ERROR_RECAPTCHA_INVALID);
                errors.rejectValue("recaptchaResponse", ERROR_RECAPTCHA_INVALID);
                System.out.println("invalid");
            }
        } catch (RecaptchaServiceException e) {
           errors.reject(ERROR_RECAPTCHA_UNAVAILABLE);
            System.out.println("unavailable");
        }
        System.out.println("valid");
    }

    private String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}

