package com.registration.reg.controller;

/**
 * Created by Stasia on 16.03.17.
 */

import com.registration.reg.model.Assortment;
import com.registration.reg.service.AssortmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.registration.reg.requestBody.AssortmentRequestBody;

@Controller("AssortmentController")
public class AssortmentController {
    @Autowired
    AssortmentService assortmentService;

    @RequestMapping(value = "/assortment", method = RequestMethod.GET)
    public ResponseEntity findAllAssortment() {
        Iterable<Assortment> assortmentList = assortmentService.findAll();
        ResponseEntity responseEntity = new ResponseEntity<>(assortmentList, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/assortment", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addAssortment(@RequestBody AssortmentRequestBody assortmentRequestBody) {
        assortmentService.save(assortmentRequestBody);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/assortment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAssortment(@PathVariable("id") Long id) {
        assortmentService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
