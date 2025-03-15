package com.springboot.crudapp.dao;

import com.springboot.crudapp.entity.Course;
import com.springboot.crudapp.entity.Instructor;
import com.springboot.crudapp.entity.InstructorDetail;
import com.springboot.crudapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao{

    private EntityManager entityManager;

    public AppDaoImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }
    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class,id);
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findByInstructorDetailId(int id) {
        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    public List<Course> findCoursesById(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id= :data",Course.class
        );
        query.setParameter("data",id);
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructotByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                +"JOIN FETCH i.courses "
                        +"JOIN FETCH i.instructorDetail "
                +"where i.id =:data", Instructor.class);
        query.setParameter("data",id);
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                + "JOIN FETCH c.review "
                + "where c.id =:data",Course.class
        );
        query.setParameter("data",id);
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id =:data",Course.class
        );
        query.setParameter("data",id);
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Student findCourseAndStudentByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        + "JOIN FETCH s.courses "
                        + "where s.id =:data",Student.class
        );
        query.setParameter("data",id);
        Student student = query.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }
}
