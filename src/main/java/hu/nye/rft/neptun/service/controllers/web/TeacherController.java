package hu.nye.rft.neptun.service.controllers.web;

import hu.nye.rft.neptun.model.Entities.Subject;
import hu.nye.rft.neptun.model.Entities.Teacher;
import hu.nye.rft.neptun.model.forms.SubjectForm;
import hu.nye.rft.neptun.service.exceptions.UserNotFoundException;
import hu.nye.rft.neptun.service.services.subject.SubjectServiceInterface;
import hu.nye.rft.neptun.service.services.teacher.TeacherServiceInterface;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;

@Controller
public class TeacherController {

    @Autowired
    private TeacherServiceInterface teacherServiceInterface;
    @Autowired
    private SubjectServiceInterface subjectServiceInterface;

    public TeacherController(TeacherServiceInterface teacherServiceInterface, SubjectServiceInterface subjectServiceInterface) {
        this.teacherServiceInterface = teacherServiceInterface;
        this.subjectServiceInterface = subjectServiceInterface;
    }

    @GetMapping("/teacher/{id}")
    public String teacherPage(@PathVariable Long id, Model model) throws UserNotFoundException {
        Collection<Subject> subjects = subjectServiceInterface.getAllSubjectByTeacherId(id);
        Teacher teacher = teacherServiceInterface.getTeacherById(id);

        model.addAttribute("subjects", subjects);
        model.addAttribute("id", id);
        model.addAttribute("teacher", teacher);

        return "teacher";
    }

    @GetMapping("/teacher/{id}/addSubject")
    public String addSubjectPage(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "addSubject";
    }

    @PostMapping("/teacher/{id}/addSubject")
    public RedirectView addTeacherToDb(@ModelAttribute("subject") SubjectForm subjectForm, @PathVariable Long id) throws UserNotFoundException {
        String url = "/teacher/" + id;

        if (
                subjectForm.getName().equals("") ||
                        subjectForm.getStartTime().equals("") ||
                        subjectForm.getDayOfTheWeek().equals("")
        ) {
            url = "/teacher/" + id + "/addSubject?missing=1";
        } else {
            Subject subject = new Subject(
                    subjectForm.getName(),
                    subjectForm.getDayOfTheWeek(),
                    subjectForm.getStartTime(),
                    subjectForm.getDurationInMinutes(),
                    subjectForm.getMaxHallgato(),
                    subjectForm.getKredit(),
                    teacherServiceInterface.getTeacherById(id)
            );

            subjectServiceInterface.addSubject(subject);
        }


        return new RedirectView(url);
    }

    @GetMapping("/teacher/{id}/removeSubject/{subjectId}")
    public RedirectView removeSubject(@PathVariable Long id, @PathVariable Long subjectId) {
        subjectServiceInterface.deleteByIdOnlySubject(subjectId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/teacher/" + id + "?removed=1");
        return redirectView;
    }
}
