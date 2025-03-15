package com.springboot.crudapp.dao;

import com.springboot.crudapp.entity.Course;
import com.springboot.crudapp.entity.Instructor;
import com.springboot.crudapp.entity.InstructorDetail;
import com.springboot.crudapp.entity.Student;

import java.util.List;

public interface AppDao {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findByInstructorDetailId(int id);
    List<Course> findCoursesById(int id);
    Instructor findInstructotByIdJoinFetch(int id);
    void update(Instructor instructor);
    void save(Course course);
    Course findCourseAndReviewsByCourseId(int id);
    Course findCourseAndStudentsByCourseId(int id);
    Student findCourseAndStudentByStudentId(int id);
    void update(Student student);
}
