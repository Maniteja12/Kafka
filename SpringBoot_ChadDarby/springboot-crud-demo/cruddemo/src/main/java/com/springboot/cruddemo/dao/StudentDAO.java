package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

        void save(Student student);

        Student findById(Integer id);
        List<Student> findAll();
        List<Student> findByLastName(String lastName);
        void updateStudent(Student student);
        void delete(Integer id);
}
