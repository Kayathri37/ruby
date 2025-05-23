package com.corfield.demoproject.Facade;

import com.corfield.demoproject.Dto.ResponseDto;
import com.corfield.demoproject.Dto.UserServiceReqDto;
import com.corfield.demoproject.Entities.LoginEmployee;
import com.corfield.demoproject.Service.UserService;
import com.corfield.demoproject.Repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserFacadeImpl {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Method to create a user
    public ResponseDto createUser(UserServiceReqDto reqDto) {
        LoginEmployee employee = reqDto.getloginEmployee();
        Optional<LoginEmployee> existingEmployee = employeeRepository.findByEmailAndPhone(employee.getEmail(), employee.getPhone());

        if (existingEmployee.isPresent()) {
            return new ResponseDto("Error: Phone number or email already in use", false);
        }
        return userService.createUser(reqDto);
    }

    // Method to update a user
    public ResponseDto updateUser(UserServiceReqDto reqDto) {
        Optional<LoginEmployee> employeeOptional = employeeRepository.findByEmailAndPhone(reqDto.getEmail(), reqDto.getPhone());
        if (employeeOptional.isPresent()) {
            return userService.updateUser(reqDto); // Call service to update the user
        } else {
            return new ResponseDto("User not found", false);
        }
    }

    // Method to delete a user
    public ResponseDto deleteUser(UserServiceReqDto reqDto) {
        Optional<LoginEmployee> employeeOptional = employeeRepository.findByEmailAndPassword(reqDto.getEmail(), reqDto.getPassword());
        if (employeeOptional.isPresent()) {
            return userService.deleteUser(reqDto); // Call service to delete the user
        } else {
            return new ResponseDto("User not found", false);
        }
    }
}