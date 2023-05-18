package com.example.homework14.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Year;
import java.util.Date;

@AllArgsConstructor
@Data
public class Employee {

    @NotEmpty(message = "Id should not be empty")
    @Size(min = 2, message = "Id length must be more than 2")
    private String id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 4, message = "Name length must be more than 4")
    private String name;

    @NotNull(message = "Age should not be empty")
    @Min(value = 25, message = "Age must be greater than or equal to 25")
    //@Digits(integer = 2, fraction = 0, message = "Age must be a number")
    @Pattern(regexp = "^[0-9]*$",message = "Should be number")
    private String age;

    @NotEmpty(message = "Role should not be empty")
    @Pattern(regexp = "\\b(?:supervisor|coordinator)\\b",message = "Role Not Valid")
    private String role;

    @AssertFalse(message = "On Leave Should be false")
    private boolean onLeave;

    @NotNull(message = "Employment Year Should not be empty")
    @PastOrPresent(message = "Should be vail year")
    private Year employmentYear;

    @NotNull(message = "Should not be empty")
    //@Pattern(regexp = "[\\\\s]*[0-9]*[1-9]+",message = "Annual Leave Should be number")
    //@Digits(integer = 2, fraction = 0, message = "Annual Leave must be a number")
    @PositiveOrZero(message = "Annual Leave Should be positive number")
    private int annualLeave;
}
