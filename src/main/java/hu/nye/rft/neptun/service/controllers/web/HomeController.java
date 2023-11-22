package hu.nye.rft.neptun.service.controllers.web;

import hu.nye.rft.neptun.model.Entities.Student;
import hu.nye.rft.neptun.model.Entities.Teacher;
import hu.nye.rft.neptun.model.forms.LoginForm;
import hu.nye.rft.neptun.model.forms.RegisterForm;
import hu.nye.rft.neptun.service.services.student.StudentServiceInterface;
import hu.nye.rft.neptun.service.services.teacher.TeacherServiceInterface;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {
    @Autowired
    private StudentServiceInterface studentServiceInterface;
    @Autowired
    private TeacherServiceInterface teacherServiceInterface;

    public HomeController(StudentServiceInterface studentServiceInterface, TeacherServiceInterface teacherServiceInterface) {
        this.studentServiceInterface = studentServiceInterface;
        this.teacherServiceInterface = teacherServiceInterface;
    }

    @GetMapping("/")
    public String loginPage(@RequestParam(name = "badLogin", required = false, defaultValue = "0") int badLogin, Model model) {
        model.addAttribute("badLogin", badLogin);
        return "login";
    }

    @PostMapping("/")
    public RedirectView loginPageWithPost(@ModelAttribute("user") LoginForm loginForm) {
        RedirectView redirectView = new RedirectView();

        if (loginForm.getType().equals("teacher")) {
            Long teacherID = teacherServiceInterface.getTeacherIdByUsername(loginForm.getUsername());
            if (teacherID == null) {
                redirectView.setUrl("/?badLogin=1");
            } else {
                String url = "/teacher/" + teacherID.toString();
                redirectView.setUrl(url);
            }
        } else {
            Long studentID = studentServiceInterface.getStudentIdByUsername(loginForm.getUsername());
            if (studentID == null) {
                redirectView.setUrl("/?badLogin=1");
            } else {
                String url = "/student/" + studentID.toString();
                redirectView.setUrl(url);
            }
        }

        return redirectView;
    }

    @GetMapping("/register")
    public String register( @RequestParam(name = "missing", required = false, defaultValue = "0") int missing,
                            @RequestParam(name = "exists", required = false, defaultValue = "0") int exists,
                            Model model) {
                            model.addAttribute("exists", exists);
                            model.addAttribute("missing", missing);
        return "register";
    }

    @PostMapping("/register")
    public RedirectView registerUserToDb(@ModelAttribute("user") RegisterForm registerForm) {
        RedirectView redirectView = new RedirectView();

        if (registerForm.getName().equals("") || registerForm.getUsername().equals("")) {
            redirectView.setUrl("/register?missing=1");
        } else {
            if (registerForm.getType().equals("teacher")) {
                if (!teacherServiceInterface.existsTeacherByUserName(registerForm.getUsername())) {
                    Teacher teacher = new Teacher(
                            registerForm.getName(),
                            registerForm.getUsername()
                    );

                    teacherServiceInterface.addTeacher(teacher);
                    redirectView.setUrl("/?registerSuccess=1");
                } else {
                    redirectView.setUrl("/register?exists=1");
                }

            } else {
                if (!studentServiceInterface.existsStudentByUserName(registerForm.getUsername())) {
                    Student student = new Student(
                            registerForm.getName(),
                            registerForm.getUsername()
                    );

                    studentServiceInterface.addStudent(student);
                    redirectView.setUrl("/?registerSuccess=1");
                } else {
                    redirectView.setUrl("/register?exists=1");
                }
            }
        }

        return redirectView;
    }
}
