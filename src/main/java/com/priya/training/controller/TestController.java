package com.priya.training.controller;

import com.priya.training.models.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        employees.putIfAbsent(employees.size() + 1, employeeName);
        return "new Employee added";
    }

    @RequestMapping(value="putEmployeesTest/{id}/{employeeName}",method = RequestMethod.PUT)
    public void putEmployeesTest(@PathVariable Integer id,@PathVariable String employeeName) {
        employees.replace(id,employeeName);
    }

    //returning status using HttpStatus
    @RequestMapping(value="deleteEmployeesTest/{id}",method= RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteEmployeesTest(@PathVariable Integer id) {
        if(employees.containsKey(id)) {
            employees.remove(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }
        else
        return new ResponseEntity<>(id,HttpStatus.NOT_FOUND);
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
    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody Employee employee)
    {
        employeesdetails.put(employee.getId(),employee);
        return "new Employee added";
    }

    @PutMapping("/updateEmployee")
    public String updateEmployee(Integer id,String phoneNumber)
    {
        if(employeesdetails.containsKey(id)) {
            Employee newEmployee = employeesdetails.get(id);
            newEmployee.setPhoneNumber(phoneNumber);
            employeesdetails.replace(id, newEmployee);
            return "Updated";
        }
        return "Employee Not Found";
    }

    @DeleteMapping("/deleteEmployee")
    public String deleteEmployee(Integer id)
    {
        if(employeesdetails.containsKey(id)) {
            employeesdetails.remove(id);
            return "Deleted";
        }
        return "Employee Not Found";
    }
}

