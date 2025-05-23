package com.corfield.demoproject.Dto;

import com.corfield.demoproject.Entities.LoginEmployee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private String message;
    private boolean success;

}