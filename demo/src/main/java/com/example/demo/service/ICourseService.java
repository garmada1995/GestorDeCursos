
package com.example.demo.service;

import com.example.demo.entity.Course;
import java.util.List;


public interface ICourseService {
    
    List<Course> getAllCourses();
    
    List<Course> getCourseByName(String name);
    
    List<Course> getCourseByFee(double fee);
    
    Course saveCourse(Course course);
    
    Course getCourseById(Long id);
    
    Course updateCourse(Course course);
    
    void deleteCourseById(Long id);
    
}
