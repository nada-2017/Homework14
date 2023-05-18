package com.example.homework14.Controller;

import com.example.homework14.ApiResponse.ApiResponse;
import com.example.homework14.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>();

    //Get all the Employees
    @GetMapping("/get")
    public ArrayList getEmployees(){
        return employees;
    }


    //Add new Employee
    @PostMapping("/add")
    public ResponseEntity addEmployee(@Valid@RequestBody Employee employee, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.add(employee);
        return ResponseEntity.status(200).body("Employee added");
    }


    //Update Employee
    @PutMapping("update/{index}")
    public ResponseEntity updateEmployee(@PathVariable int index, @Valid@RequestBody Employee employee, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.set(index,employee);
        return ResponseEntity.status(200).body("Employee updated");
    }


    //Delete Employee
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index){
        employees.remove(index);
        return ResponseEntity.status(200).body("Employee deleted");
    }



    //Employees apply for an annual leave
    @PutMapping("/vacation/{index}")
    public ResponseEntity vacation(@PathVariable int index){
        if (employees.get(index).isOnLeave())
            return ResponseEntity.status(400).body("Employee already on leave");
        else if (employees.get(index).getAnnualLeave() <= 0)
            return ResponseEntity.status(400).body("Not enough");
        employees.get(index).setAnnualLeave(employees.get(index).getAnnualLeave()-1);
        employees.get(index).setOnLeave(true);
        return ResponseEntity.status(200).body("Employee on leave");
    }
}
