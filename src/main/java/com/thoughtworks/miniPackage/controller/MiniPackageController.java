package com.thoughtworks.miniPackage.controller;

import com.thoughtworks.miniPackage.model.MiniPackage;
import com.thoughtworks.miniPackage.service.MiniPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/miniPackage")
public class MiniPackageController {

    @Autowired
    MiniPackageService miniPackageService;


    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<MiniPackage> createPackage
            (@RequestBody MiniPackage miniPackage)
    {
                miniPackageService.addPackage(miniPackage);
            return new ResponseEntity<>(miniPackage , HttpStatus.CREATED);
    }

    @GetMapping(produces = {"application/json"})
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<MiniPackage> getAllPackages(
            @RequestParam(required = false , defaultValue = "0")  Integer page ,
            @RequestParam(required = false , defaultValue = "5")Integer size ) {
        return miniPackageService.findAll(PageRequest.of(page,size, Sort.by("waybillNumber").ascending()));
    }

}
