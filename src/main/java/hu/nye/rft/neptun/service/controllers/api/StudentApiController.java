package hu.nye.rft.neptun.service.controllers.api;

import hu.nye.rft.neptun.model.Entities.Student;
import hu.nye.rft.neptun.service.services.student.StudentServiceInterface;
import hu.nye.rft.neptun.service.services.subject.SubjectServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class StudentApiController {

    @Autowired
    private StudentServiceInterface studentServiceInterface;

    @Autowired
    private SubjectServiceInterface subjectServiceInterface;

    @GetMapping("/api/student")
    public Collection<Student> getAllStudent() {
        return studentServiceInterface.getAllStudent();
    }

    @DeleteMapping("/api/student/remove/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentServiceInterface.removeStudentById(id);
    }
}
