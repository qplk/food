package com.registration.reg.validator;

import com.registration.reg.model.User;
import com.registration.reg.requestBody.UserRequestBody;
import com.registration.reg.service.SecurityService;
import com.registration.reg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    private static final String patternEmailString = "([A-Za-z0-9]{1,}[\\\\-]{0,1}[A-Za-z0-9]{1,}[\\\\.]{0,1}[A-Za-z0-9]{1,})+@([A-Za-z0-9]{1,}[\\\\-]{0,1}[A-Za-z0-9]{1,}[\\\\.]{0,1}[A-Za-z0-9]{1,})+[\\\\.]{1}[a-z]{2,4}";
    private static final Pattern patternEmail = Pattern.compile(patternEmailString);

    private static String patternPhoneString = "^\\+?[0-9. ()-]{10,25}$";
    Pattern patternPhone = Pattern.compile(patternPhoneString);

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        validateFields(user, errors);
    }

    public void validateFields(User user, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

        if(!patternEmail.matcher(user.getEmail()).matches()){
            errors.rejectValue("email", "Match.userForm.email");
        }

        if(!patternPhone.matcher(user.getPhoneNumber()).matches()){
            errors.rejectValue("phoneNumber", "Match.userForm.phoneNumber");
        }
    }

    public void validateUpdate(UserRequestBody user, Errors errors) {

        if(!patternEmail.matcher(user.getEmail()).matches()){
            errors.rejectValue("email", "Match.userForm.email");
        }

        if(!patternPhone.matcher(user.getPhoneNumber()).matches()){
            errors.rejectValue("phoneNumber", "Match.userForm.phoneNumber");
        }
    }

    public void validatePassword(UserRequestBody userRequestBody, Errors errors) {

        User user = userService.get(userRequestBody.getUserId());



        if (!bCryptPasswordEncoder.matches(userRequestBody.getPassword(), user.getPassword())) {
            errors.rejectValue("password", "Wrong.passwordForm.password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "NotEmpty");
        if (userRequestBody.getPassword().length() < 8 || userRequestBody.getPassword().length() > 32) {
            errors.rejectValue("newPassword", "Size.passwordForm.newPassword");
        }

        System.out.println(userRequestBody.getNewPasswordConfirm());
        System.out.println(userRequestBody.getPassword());

        if (!userRequestBody.getNewPasswordConfirm().equals(userRequestBody.getNewPassword())) {
            errors.rejectValue("newPasswordConfirm", "Diff.passwordForm.newPasswordConfirm");
        }



    }
}
