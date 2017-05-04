package com.registration.reg.validator;

import com.registration.reg.model.Address;
import com.registration.reg.model.User;
import com.registration.reg.requestBody.AddressRequestBody;
import com.registration.reg.service.AddressService;
import com.registration.reg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Stasia on 03.05.17.
 */
@Component
public class AddressValidator implements Validator {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return AddressRequestBody.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AddressRequestBody addressRequestBody = (AddressRequestBody) o;
        Address address = addressService.findAddress(addressRequestBody);
        User user = userService.get(addressRequestBody.getUserId());

        if (address != null) {
            if (address.getUsers().contains(user)) {
                System.out.println("QWERTY");
                errors.rejectValue("addressId", "Duplicate.addressForm.address");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buildingNumber", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roomNumber", "NotEmpty");

    }
}