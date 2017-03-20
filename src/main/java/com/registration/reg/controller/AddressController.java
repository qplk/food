package com.registration.reg.controller;

import com.registration.reg.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.registration.reg.model.Address;
import com.registration.reg.requestBody.AddressRequestBody;
/**
 * Created by Stasia on 15.03.17.
 */
@Controller("AddressController")
public class AddressController {
    @Autowired
    AddressService addressService;

    @RequestMapping(value = "/addresses", method = RequestMethod.GET)
    public ResponseEntity findAllAddresses() {
        Iterable<Address> addressList = addressService.findAll();
        ResponseEntity responseEntity = new ResponseEntity<>(addressList, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/addresses", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addAddress(@RequestBody AddressRequestBody addressRequestBody) {
        addressService.save(addressRequestBody);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/addresses/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAddress(@PathVariable("id") Long id) {
        addressService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
