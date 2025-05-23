package com.corfield.demoproject.Service;

import com.corfield.demoproject.Dto.ResponseDto;
import com.corfield.demoproject.Dto.UserServiceReqDto;
import com.corfield.demoproject.Entities.LoginEmployee;
import com.corfield.demoproject.Repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<LoginEmployee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public ResponseDto createUser(UserServiceReqDto reqDto) {
        LoginEmployee employee = reqDto.getloginEmployee();
        try {
            employeeRepository.save(employee);
            return new ResponseDto("Employee created successfully", true);
        } catch (DataIntegrityViolationException e) {
            return new ResponseDto("Error: Phone number or email already exists", false);
        } catch (Exception e) {
            return new ResponseDto("Error: " + e.getMessage(), false);
        }
    }

    @Override
    public ResponseDto loginUser(UserServiceReqDto reqDto) {
        try {
            LoginEmployee employee = reqDto.getloginEmployee();  // Get the LoginEmployee from reqDto

            // Check for employee by email, password, phone, and name
            Optional<LoginEmployee> employeeOptional = employeeRepository.findByEmailAndPasswordAndPhoneAndName(
                    employee.getEmail(),
                    employee.getPassword(),
                    employee.getPhone(),
                    employee.getName()
            );

            if (employeeOptional.isPresent()) {
                return new ResponseDto("Login successful", true);
            } else {
                return new ResponseDto("Login failed: Incorrect email, password, phone, or name", false);
            }
        } catch (Exception e) {
            return new ResponseDto("Error: " + e.getMessage(), false);
        }
    }

    @Override
    public ResponseDto updateUser(UserServiceReqDto reqDto) {
        try {
            // Query the employee by email and phone
            Optional<LoginEmployee> employeeOptional = employeeRepository.findByEmailAndPhone(reqDto.getEmail(), reqDto.getPhone());

            if (employeeOptional.isPresent()) {
                LoginEmployee employee = employeeOptional.get();

                // Update the employee's details
                employee.setName(reqDto.getName());  // Update name
                employee.setEmail(reqDto.getEmail());  // Update email
                employee.setPhone(reqDto.getPhone());  // Update phone
                employee.setPassword(reqDto.getPassword()); // Update password

                employeeRepository.save(employee); // Save updated employee

                return new ResponseDto("User details updated successfully", true);
            } else {
                return new ResponseDto("Update failed: Invalid user", false);
            }
        } catch (Exception e) {
            return new ResponseDto("Error: " + e.getMessage(), false);
        }
    }

    @Override
    public ResponseDto deleteUser(UserServiceReqDto reqDto) {
        try {
            // Get the LoginEmployee from reqDto
            LoginEmployee employee = reqDto.getloginEmployee();

            // Use the email and password from the employee object for deletion
            Optional<LoginEmployee> employeeOptional = employeeRepository.findByEmailAndPassword(employee.getEmail(), employee.getPassword());

            if (employeeOptional.isPresent()) {
                employeeRepository.delete(employeeOptional.get()); // Delete the user
                return new ResponseDto("Account Deleted", true);
            } else {
                return new ResponseDto("Delete failed: User not found", false);
            }
        } catch (Exception e) {
            return new ResponseDto("Error: " + e.getMessage(), false);
        }
    }

}