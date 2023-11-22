package hu.nye.rft.neptun.service.controllers.web;

import hu.nye.rft.neptun.model.Entities.Student;
import hu.nye.rft.neptun.model.Entities.Subject;
import hu.nye.rft.neptun.service.exceptions.UserNotFoundException;
import hu.nye.rft.neptun.service.services.student.StudentServiceInterface;
import hu.nye.rft.neptun.service.services.subject.SubjectServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;

@Controller
public class StudentController {

    @Autowired
    private StudentServiceInterface studentServiceInterface;

    @Autowired
    private SubjectServiceInterface subjectServiceInterface;

    public StudentController(StudentServiceInterface studentServiceInterface, SubjectServiceInterface subjectServiceInterface) {
        this.studentServiceInterface = studentServiceInterface;
        this.subjectServiceInterface = subjectServiceInterface;
    }

    @GetMapping("/student/{id}")
    public String studentPage(@PathVariable Long id, Model model) throws UserNotFoundException {
        Student student = studentServiceInterface.getStudentById(id);
        Collection<Subject> subjects = subjectServiceInterface.getAllSubjectThatHasStudentId(student.getStudentId());

        model.addAttribute("student", student);
        model.addAttribute("subjects", subjects);

        return "student";
    }

    @GetMapping("/student/{id}/enrollSubject")
    public String enrollSubjectPage(@PathVariable Long id, Model model) {
        Collection<Subject> allSubject = subjectServiceInterface.getAllSubjectThatDoesNotHaveStudentId(id);

        model.addAttribute("id", id);
        model.addAttribute("subjects", allSubject);
        return "enrollSubject";
    }

    @GetMapping("/subject/{id}/enrollSubject/{subjectId}")
    public RedirectView enrollSubject(@PathVariable Long id, @PathVariable Long subjectId) throws UserNotFoundException {
        Student student = studentServiceInterface.getStudentById(id);
        Subject subject = subjectServiceInterface.getSubjectBySubjectId(subjectId);
        subject.addStudent(student);
        subjectServiceInterface.addSubject(subject);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/student/{id}/enrollSubject");
        return redirectView;
    }

    @GetMapping("/student/{id}/leaveSubject/{subjectId}")
    public RedirectView leaveSubject(@PathVariable Long id, @PathVariable Long subjectId) throws UserNotFoundException {
        Subject subject = subjectServiceInterface.getSubjectBySubjectId(subjectId);
        subject.removeStudent(id);
        subjectServiceInterface.addSubject(subject);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/student/{id}");
        return redirectView;
    }
}