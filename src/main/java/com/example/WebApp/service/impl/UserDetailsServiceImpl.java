package com.example.WebApp.service.impl;

import com.example.WebApp.domain.dto.UserDetailsDto;
import com.example.WebApp.domain.entity.UserDetails;
import com.example.WebApp.repository.UserDetailsRepository;
import com.example.WebApp.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetailsDto create(UserDetailsDto userDetailsDto) throws Exception {
        UserDetails details= userDetailsRepository.findByUsername(userDetailsDto.getUsername());

        if (details != null){
            throw new Exception("ssa");
        }
        else {
            details = new UserDetails();
            details.setUsername(userDetailsDto.getUsername());

/*            String passwordInput = userDetailsDto.getPassword();
            String encodedBase64Password = Base64.getEncoder().encodeToString(passwordInput.getBytes());*/
            details.setPassword(userDetailsDto.getPassword());
            details.setEnabled(userDetailsDto.isEnabled());

            details = userDetailsRepository.save(details);
            userDetailsDto.setId(details.getId());
        }
        return userDetailsDto;

    }
}
