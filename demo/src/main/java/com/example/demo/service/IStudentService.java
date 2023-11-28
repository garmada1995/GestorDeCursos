package com.example.demo.service;

import com.example.demo.entity.Student;
import java.util.List;

public interface IStudentService {
    
    List<Student> getAllStudents();
    
    List<Student> getStudentByName(String name);
    
    Student saveStudent(Student student);
    
    Student getStudentById(Long id);
    
    Student updateStudent(Student student);
    
    void deleteStudentById(Long id);

}
