package com.priya.training.controller;

import com.priya.training.models.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.StringValueExp;
import javax.naming.InvalidNameException;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

@Controller
@ResponseBody
public class TestController {

    @GetMapping("/getTest")
    public String getTest() {
        return "Get Mapping done";
    }
    @PostMapping("/postTest")
    public String postTest(){
        return "Post Mapping done";
    }
    //CRUD using HashMap
    Map<Integer,String> employees=new HashMap<>();

    @GetMapping("/getEmployeesTest")
    public String getEmployeesTest() {
        //Iterator to print HashMap
        Iterator itr=employees.entrySet().iterator();
        while(itr.hasNext())
            System.out.println(itr.next());
        //Streams to print HashMap
        employees.entrySet().stream().forEach(e-> System.out.println(e));
        return employees.toString();
    }

    @PostMapping("/postEmployeesTest")
    public String postEmployeesTest(String employeeName) {
        try {
            validateEmployeeName(employeeName);
            employees.put(employees.size() + 1, employeeName);
            return "Employee added successfully";
        }
        catch(Exception e) {
            return e+" ";
        }
    }

    static void validateEmployeeName(String employeeName)throws InvalidEmployeeNameException{
        if(employeeName != null && employeeName.matches("^[a-zA-Z]*$"))
            System.out.println("new Employee added");
        else
            throw new InvalidEmployeeNameException("not a valid Name");

    }


    @RequestMapping(value="putEmployeesTest/{id}/{employeeName}",method = RequestMethod.PUT)
    public String putEmployeesTest(@PathVariable String id,@PathVariable String employeeName) {
        try {
            Integer tempId=Integer.parseInt(id);
            validateEmployeeName(employeeName);
            if (!employees.containsKey(tempId)) { return "Employee Not Found"; }
            employees.replace(tempId, employeeName);
            return "Updated";
        }
        catch(NumberFormatException e){ return e+""; }
        catch(InvalidEmployeeNameException e) { return e+" "; }
    }

    //returning status using HttpStatus
    @RequestMapping(value="deleteEmployeesTest/{id}",method= RequestMethod.DELETE)
    public ResponseEntity<String> deleteEmployeesTest(@PathVariable String id) {
        try {
            Integer tempId = Integer.parseInt(id);
            if (employees.containsKey(tempId)) {
                employees.remove(tempId);
                return new ResponseEntity<>(id, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(id,HttpStatus.NOT_FOUND);
        }
        catch(NumberFormatException e) { return new ResponseEntity<>(id,HttpStatus.BAD_REQUEST); }
    }
    //CRUD Using Employee class in HashMap

    Map<Integer,Employee> employeesdetails=new HashMap<>();

    @GetMapping("/retrieveEmployee")
    public void retrieveEmployee()
    {
        if(employeesdetails.isEmpty())
            System.out.println("No Employees Yet");
        else
        for(Integer id : employeesdetails.keySet())
        System.out.println(id+" "+employeesdetails.get(id).getPhoneNumber() );
    }
    @RequestMapping(value="addEmployee",method = RequestMethod.POST)
    public String addEmployee(@RequestBody Employee employee) {
        employeesdetails.put(employee.getId(),employee);
        return "new Employee added";
    }

    @PutMapping("/updateEmployee")
    public String updateEmployee( String id, String phoneNumber) {
        try {
            Integer tempId=Integer.parseInt(id);
            Long tempPhoneNumber=Long.parseLong(phoneNumber);
            if (!employeesdetails.containsKey(tempId)) { return "Employee Not Found"; }
            Employee newEmployee = employeesdetails.get(tempId);
            newEmployee.setPhoneNumber(phoneNumber);
            employeesdetails.replace(tempId, newEmployee);
            return "Updated";
        }
        catch(NumberFormatException e){ return e+""; }
    }

    @DeleteMapping("/deleteEmployee")
    public String deleteEmployee(String id) {
        try {
            Integer.parseInt(id);
            if (employeesdetails.containsKey(id)) {
                employeesdetails.remove(id);
                return "Deleted";
            }
            return "Employee Not Found";
        }
        catch(NumberFormatException e){
            return e+" ";
        }
    }
}

