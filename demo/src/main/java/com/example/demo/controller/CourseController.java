package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.repository.ICourseRepository;
import com.example.demo.service.CourseService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    @Autowired
    private ICourseRepository courseRepository;

    private List<Course> coursesList = new ArrayList<>();

    public CourseController(CourseService courseService, ICourseRepository courseRepository) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
        
        this.coursesList = this.courseRepository.findAllSortByName();
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";
    }    
    
    @GetMapping("/courses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";
    }
    
    @GetMapping("/courses/new")
    public String createCourseForm(Model model){
        
        // este objeto Course almacenara los valores 
        Course course = new Course();
       
        model.addAttribute("course", course);
        model.addAttribute("coursesList", coursesList);

        return "create_course";
    }
    
    @PostMapping("/courses")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }
    
    @GetMapping("/courses/edit/{id}")
    public String editCourseForm(@PathVariable Long id, Model model) {
        Course st = courseService.getCourseById(id);
        
        model.addAttribute("course", st);
        model.addAttribute("coursesList", coursesList);
        
        return "edit_course";
    }
    
    @PostMapping("/courses/{id}")
    public String updateCourse(@PathVariable Long id, 
            @ModelAttribute("course") Course course,
            Model model) {
        //sacar el curso de la b.d. por el id
        Course existentCourse = courseService.getCourseById(id);
        // cargarlo
        existentCourse.setId(id);
        existentCourse.setName(course.getName());
        existentCourse.setModules(course.getModules());
        existentCourse.setCredits(course.getCredits());
        existentCourse.setFee(course.getFee());

        // guardar el curso actualizado
        courseService.updateCourse(existentCourse);
        
        return "redirect:/courses";
    }
    
    @GetMapping("/courses/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }
}
