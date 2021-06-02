package com.example.WebApp.resource;

import com.example.WebApp.domain.dto.UserDetailsDto;
import com.example.WebApp.domain.entity.UserDetails;
import com.example.WebApp.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserDetailsController {
    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/")
    private ResponseEntity<UserDetails> create(@RequestBody UserDetailsDto userDetailsDto) throws Exception {
        return new ResponseEntity(userDetailsService.create(userDetailsDto), HttpStatus.OK);
    }
}
