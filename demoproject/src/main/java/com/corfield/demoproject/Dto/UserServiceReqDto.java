package com.corfield.demoproject.Dto;

import com.corfield.demoproject.Entities.LoginEmployee;
import lombok.Data;

@Data
public class UserServiceReqDto {
    private String phone;    // Phone number of the user
    private String email;    // Email of the user
    private String password; // Password of the user
    private String name;     // Name of the user

    // This method will return a LoginEmployee object with the details set
    public LoginEmployee getloginEmployee() {
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setName(this.name);
        loginEmployee.setEmail(this.email);
        loginEmployee.setPhone(this.phone);
        loginEmployee.setPassword(this.password);
        loginEmployee.setActive(true);  // Assuming the user is active by default
        return loginEmployee;
    }
}