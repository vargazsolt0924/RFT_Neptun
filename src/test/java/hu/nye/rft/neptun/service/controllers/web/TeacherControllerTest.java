package hu.nye.rft.neptun.service.controllers.web;

import hu.nye.rft.neptun.model.Entities.Subject;
import hu.nye.rft.neptun.model.Entities.Teacher;
import hu.nye.rft.neptun.model.forms.SubjectForm;
import hu.nye.rft.neptun.service.exceptions.UserNotFoundException;
import hu.nye.rft.neptun.service.services.subject.SubjectServiceInterface;
import hu.nye.rft.neptun.service.services.teacher.TeacherServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

class TeacherControllerTest {
    private TeacherController underTest;

    @Mock
    private TeacherServiceInterface teacherServiceInterface;

    @Mock
    private SubjectServiceInterface subjectServiceInterface;

    @Mock
    private SubjectForm subjectForm;

    @Mock
    private Model model;

    @Mock
    private Collection<Subject> subjects;

    @Mock
    private Teacher teacher = new Teacher("name", "username");

    private Long id = Long.valueOf(1);

    private Long tantargyId = Long.valueOf(2);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new TeacherController(teacherServiceInterface, subjectServiceInterface);
    }

    @Test
    public void testTeacherPage() throws UserNotFoundException {
        //given
        given(subjectServiceInterface.getAllSubjectByTeacherId(id)).willReturn(subjects);
        given(teacherServiceInterface.getTeacherById(id)).willReturn(teacher);

        //when
        String result = underTest.teacherPage(id, model);

        //then
        verify(subjectServiceInterface).getAllSubjectByTeacherId(id);
        verify(teacherServiceInterface).getTeacherById(id);
        verify(model).addAttribute("subjects", subjects);
        verify(model).addAttribute("id", id);
        verify(model).addAttribute("teacher", teacher);
        assertEquals(result, "teacher");
    }

    @Test
    public void testAddSubjectPage() {
        //given

        //when
        String result = underTest.addSubjectPage(id, model);

        //then
        verify(model).addAttribute("id", id);
        assertEquals(result, "addSubject");
    }

    @Test
    public void testAddSubjectToDbShouldGiveBackAddSubjectMissingUrl() throws UserNotFoundException {
        //given
        given(subjectForm.getName()).willReturn("");

        //when
        RedirectView redirectView = underTest.addSubjectToDb(subjectForm, id);

        //then
        verify(subjectForm).getName();
        assertEquals(redirectView.getUrl(), "/teacher/1/addSubject?missing=1");
    }

    @Test
    public void testAddSubjectToDbShouldGiveBackCorrectUrlWhenSubjectExists() throws UserNotFoundException {
        //given
        given(subjectForm.getName()).willReturn("name");
        given(subjectForm.getStartTime()).willReturn("start");
        given(subjectForm.getDayOfTheWeek()).willReturn("day");

        //when
        RedirectView redirectView = underTest.addSubjectToDb(subjectForm, id);

        //then
        verify(subjectForm, atLeast(2)).getName();
        verify(subjectForm, atLeast(2)).getDayOfTheWeek();
        verify(subjectForm, atLeast(2)).getStartTime();
        verify(subjectForm).getDurationInMinutes();
        verify(subjectForm).getMaxHallgato();
        verify(subjectForm).getCredit();
        verify(teacherServiceInterface).getTeacherById(id);
        assertEquals(redirectView.getUrl(), "/teacher/1");
    }

    @Test
    public void testRemoveSubject() {
        //given

        //when
        RedirectView redirectView = underTest.removeSubject(id, tantargyId);

        //then
        verify(subjectServiceInterface).deleteByIdOnlySubject(tantargyId);
        assertEquals(redirectView.getUrl(), "/teacher/1?removed=1");
    }

}