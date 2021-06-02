package com.example.WebApp.service;

import com.example.WebApp.domain.dto.UserDetailsDto;

public interface UserDetailsService {

    UserDetailsDto create (UserDetailsDto userDetailsDto) throws Exception;

}
