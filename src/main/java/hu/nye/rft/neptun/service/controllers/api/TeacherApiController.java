package hu.nye.rft.neptun.service.controllers.api;

import hu.nye.rft.neptun.model.Entities.Subject;
import hu.nye.rft.neptun.model.Entities.Teacher;
import hu.nye.rft.neptun.service.services.student.StudentServiceInterface;
import hu.nye.rft.neptun.service.services.subject.SubjectServiceInterface;
import hu.nye.rft.neptun.service.services.teacher.TeacherServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TeacherApiController {
    @Autowired
    private StudentServiceInterface studentServiceInterface;

    @Autowired
    private SubjectServiceInterface subjectServiceInterface;

    @Autowired
    private TeacherServiceInterface teacherServiceInterface;

    @GetMapping("/api/teacher")
    public Collection<Teacher> getAllTeacher() {
        return teacherServiceInterface.getAllTeacher();
    }

    @DeleteMapping("/api/teacher/remove/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherServiceInterface.removeTeacherById(id);
    }

    @GetMapping("/api/subject")
    public Collection<Subject> getAllSubject() {
        return subjectServiceInterface.getAllSubject();
    }

    @DeleteMapping("/api/subject/remove/{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectServiceInterface.deleteByIdOnlySubject(id);
    }

}
