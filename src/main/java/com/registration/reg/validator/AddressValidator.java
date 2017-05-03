package com.registration.reg.validator;

import com.registration.reg.requestBody.AddressRequestBody;
import com.registration.reg.service.AddressService;
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

    @Override
    public boolean supports(Class<?> aClass) {
        return AddressRequestBody.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AddressRequestBody address = (AddressRequestBody) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buildingNumber", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roomNumber", "NotEmpty");

    }
}