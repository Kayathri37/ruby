package com.corfield.demoproject.Service;



import com.corfield.demoproject.Dto.ResponseDto;
import com.corfield.demoproject.Dto.UserServiceReqDto;
import com.corfield.demoproject.Entities.LoginEmployee;

import java.util.List;

public interface UserService {
    ResponseDto createUser(UserServiceReqDto reqDto);
    List<LoginEmployee> getAllEmployees();
    ResponseDto loginUser(UserServiceReqDto reqDto);
    ResponseDto updateUser(UserServiceReqDto reqDto);
    ResponseDto deleteUser(UserServiceReqDto reqDto);
}