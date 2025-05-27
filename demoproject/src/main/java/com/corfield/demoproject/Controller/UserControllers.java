package com.corfield.demoproject.Controller;

import com.corfield.demoproject.Dto.ResponseDto; // Import the Response DTO
import com.corfield.demoproject.Dto.UserServiceReqDto;
import com.corfield.demoproject.Entities.LoginEmployee;
import com.corfield.demoproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/viewall")
public class UserControllers {

    @Autowired
    private UserService userService;

    // Endpoint for user registration
    @PostMapping("/signin")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserServiceReqDto reqDto) {
        ResponseDto response = userService.createUser(reqDto);
        return ResponseEntity.ok(response);// Return the ResponseDto wrapped in ResponseEntity
    }

    // Endpoint to get all users
    @GetMapping("/users")
    public ResponseEntity<List<LoginEmployee>> getAllEmployees() {
        List<LoginEmployee> employees = userService.getAllEmployees();  // Fetch all employees from service
        return ResponseEntity.ok(employees);  // Return the list of employees
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> loginUser(@RequestBody UserServiceReqDto reqDto) {
        ResponseDto response = userService.loginUser(reqDto);
        return ResponseEntity.ok(response); // Return the ResponseDto wrapped in ResponseEntity
    }

    @PutMapping("/resetpassword")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody UserServiceReqDto reqDto) {
        ResponseDto response = userService.updateUser(reqDto);
        return ResponseEntity.ok(response); // Return the ResponseDto wrapped in ResponseEntity
    }
    @DeleteMapping("/deleteaccount")
    public ResponseEntity<ResponseDto> deleteUserAccount(@RequestBody UserServiceReqDto reqDto) {
        ResponseDto response = userService.deleteUser(reqDto);  // Call the deleteUser method in the service layer
        return ResponseEntity.ok(response); // Return the ResponseDto wrapped in ResponseEntity
    }
}