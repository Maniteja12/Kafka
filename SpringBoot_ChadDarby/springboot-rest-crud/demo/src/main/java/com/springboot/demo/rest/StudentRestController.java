package com.springboot.demo.rest;

import com.springboot.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> studentList;

    @PostConstruct
    public void loadData(){
        studentList = new ArrayList<>();
        studentList.add(new Student("Tom","Holl"));
        studentList.add(new Student("Nick","Dick"));
        studentList.add(new Student("John","Albert"));
    }

    @GetMapping("/students")
    public List<Student> getStudent(){
        return studentList;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        if(studentId > studentList.size() || studentId <0)
            throw  new StudentNotFoundException("Student id not found " + studentId);
        return studentList.get(studentId);
    }
}
