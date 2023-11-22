package hu.nye.rft.neptun.service.controllers.web;

import hu.nye.rft.neptun.model.Entities.Student;
import hu.nye.rft.neptun.model.Entities.Subject;
import hu.nye.rft.neptun.model.Entities.Teacher;
import hu.nye.rft.neptun.service.exceptions.UserNotFoundException;
import hu.nye.rft.neptun.service.services.student.StudentServiceInterface;
import hu.nye.rft.neptun.service.services.subject.SubjectServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class StudentControllerTest {
    private StudentController underTest;

    private Long id = Long.valueOf(1);

    private Long subjectId = Long.valueOf(1);

    @Mock
    private Model model;

    @Mock
    private StudentServiceInterface studentServiceInterface;

    @Mock
    private SubjectServiceInterface subjectServiceInterface;

    @Mock
    private Student student = new Student("name", "username");

    @Mock
    private Collection<Subject> subjects;

    @Mock
    private Teacher teacher = new Teacher("name", "username");

    @Mock
    private Subject subject = new Subject("name", "monday", "1200", 45, 20, 3, teacher);


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new StudentController(studentServiceInterface, subjectServiceInterface);
    }

    @Test
    public void testStudentPage() throws UserNotFoundException {
        //given
        given(studentServiceInterface.getStudentById(id)).willReturn(student);
        given(subjectServiceInterface.getAllSubjectThatHasStudentId(student.getStudentId())).willReturn(subjects);

        //when
        String result = underTest.studentPage(id, model);

        //then
        verify(studentServiceInterface).getStudentById(id);
        verify(subjectServiceInterface).getAllSubjectThatHasStudentId(student.getStudentId());
        verify(model).addAttribute("student", student);
        verify(model).addAttribute("subjects", subjects);
        assertEquals(result, "student");
    }

    @Test
    public void testEnrollSubjectPage() {
        //given
        given(subjectServiceInterface.getAllSubjectThatDoesNotHaveStudentId(id)).willReturn(subjects);

        //when
        String result = underTest.enrollSubjectPage(id, model);

        //then
        verify(subjectServiceInterface).getAllSubjectThatDoesNotHaveStudentId(id);
        verify(model).addAttribute("id", id);
        verify(model).addAttribute("subjects", subjects);
        assertEquals("enrollSubject", result);
    }

    @Test
    public void testEnrollSubject() throws UserNotFoundException {
        //given
        given(studentServiceInterface.getStudentById(id)).willReturn(student);
        given(subjectServiceInterface.getSubjectBySubjectId(subjectId)).willReturn(subject);

        //when
        RedirectView redirectView = underTest.enrollSubject(id, subjectId);

        //then
        verify(studentServiceInterface).getStudentById(id);
        verify(subject).addStudent(student);
        verify(subjectServiceInterface).addSubject(subject);
        assertEquals(redirectView.getUrl(), "/student/{id}/enrollSubject");

    }

    @Test
    public void testLeaveSubject() throws UserNotFoundException {
        //given
        given(subjectServiceInterface.getSubjectBySubjectId(subjectId)).willReturn(subject);

        //when
        RedirectView redirectView = underTest.leaveSubject(id, subjectId);

        //then
        verify(subjectServiceInterface).getSubjectBySubjectId(subjectId);
        verify(subject).removeStudent(id);
        verify(subjectServiceInterface).addSubject(subject);
        assertEquals(redirectView.getUrl(), "/student/{id}");
    }
}
