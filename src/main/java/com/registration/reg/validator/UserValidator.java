package com.registration.reg.validator;

import com.registration.reg.model.User;
import com.registration.reg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.registration.reg.requestBody.UserRequestBody;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    private static final String patternEmailString = "([A-Za-z0-9]{1,}[\\\\-]{0,1}[A-Za-z0-9]{1,}[\\\\.]{0,1}[A-Za-z0-9]{1,})+@([A-Za-z0-9]{1,}[\\\\-]{0,1}[A-Za-z0-9]{1,}[\\\\.]{0,1}[A-Za-z0-9]{1,})+[\\\\.]{1}[a-z]{2,4}";
    private static final Pattern patternEmail = Pattern.compile(patternEmailString);

    @Autowired
    private UserService userService;

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
    }

    public void validateUpdate(UserRequestBody user, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if(!patternEmail.matcher(user.getEmail()).matches()){
            errors.rejectValue("email", "Match.userForm.email");
        }

    }
}
