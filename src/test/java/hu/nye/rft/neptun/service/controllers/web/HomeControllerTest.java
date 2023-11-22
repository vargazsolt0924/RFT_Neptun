package hu.nye.rft.neptun.service.controllers.web;

import hu.nye.rft.neptun.model.Entities.Teacher;
import hu.nye.rft.neptun.model.forms.LoginForm;
import hu.nye.rft.neptun.model.forms.RegisterForm;
import hu.nye.rft.neptun.service.services.student.StudentServiceInterface;
import hu.nye.rft.neptun.service.services.teacher.TeacherServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

class HomeControllerTest {
    private HomeController underTest;

    @Mock
    private StudentServiceInterface studentServiceInterface;

    @Mock
    private TeacherServiceInterface teacherServiceInterface;

    @Mock
    private Model model;

    @Mock
    private LoginForm loginForm;

    @Mock
    private RegisterForm registerForm;

    @Mock
    private Teacher teacher = new Teacher("name", "username");

    private final Long idNull = null;

    private final Long id = Long.valueOf(1);

    private final int badLogin = 1;

    private final int exists = 2;

    private final int missing = 3;

    @BeforeEach
    public void setUp() {
        underTest = new HomeController(studentServiceInterface, teacherServiceInterface);
    }

    @Test
    public void testLoginPage() {
        //given

        //when
        String result = underTest.loginPage(badLogin, model);

        //then
        verify(model).addAttribute("badLogin", badLogin);
        assertEquals(result, "login");
    }

    @Test
    public void testLoginPageWithPostShouldGiveBackBadLoginUrlWhenTeacherAndTeacherIdIsNull() {
        //given
        given(loginForm.getType()).willReturn("teacher");
        given(loginForm.getUsername()).willReturn("username");
        given(teacherServiceInterface.getTeacherIdByUsername("username")).willReturn(idNull);

        //when
        RedirectView redirectView = underTest.loginPageWithPost(loginForm);

        //then
        verify(loginForm).getType();
        verify(loginForm).getUsername();
        verify(teacherServiceInterface).getTeacherIdByUsername("username");
        assertEquals(redirectView.getUrl(), "/?badLogin=1");
    }

    @Test
    public void testLoginPageWithPostShouldGiveBackCorrectUrlWhenTeacherAndTeacherIdIsNotNull() {
        //given
        given(loginForm.getType()).willReturn("teacher");
        given(loginForm.getUsername()).willReturn("username");
        given(teacherServiceInterface.getTeacherIdByUsername("username")).willReturn(id);

        //when
        RedirectView redirectView = underTest.loginPageWithPost(loginForm);

        //then
        verify(loginForm).getType();
        verify(loginForm).getUsername();
        verify(teacherServiceInterface).getTeacherIdByUsername("username");
        assertEquals(redirectView.getUrl(), "/teacher/1");
    }

    @Test
    public void testLoginPageWithPostShouldGiveBackBadLoginUrlWhenNotTeacherAndStudentIdIsNull() {
        //given
        given(loginForm.getType()).willReturn("notteacher");
        given(loginForm.getUsername()).willReturn("username");
        given(studentServiceInterface.getStudentIdByUsername("username")).willReturn(idNull);

        //when
        RedirectView redirectView = underTest.loginPageWithPost(loginForm);

        //then
        verify(loginForm).getType();
        verify(loginForm).getUsername();
        verify(studentServiceInterface).getStudentIdByUsername("username");
        assertEquals(redirectView.getUrl(), "/?badLogin=1");
    }

    @Test
    public void testLoginPageWithPostShouldGiveBackCorrectUrlWhenNotTeacherAndStudentIdIsNotNull() {
        //given
        given(loginForm.getType()).willReturn("notteacher");
        given(loginForm.getUsername()).willReturn("username");
        given(studentServiceInterface.getStudentIdByUsername("username")).willReturn(id);

        //when
        RedirectView redirectView = underTest.loginPageWithPost(loginForm);

        //then
        verify(loginForm).getType();
        verify(loginForm).getUsername();
        verify(studentServiceInterface).getStudentIdByUsername("username");
        assertEquals(redirectView.getUrl(), "/student/1");
    }

    @Test
    public void testRegister() {
        //given

        //when
        String result = underTest.register(missing, exists, model);

        //then
        verify(model).addAttribute("exists", exists);
        verify(model).addAttribute("missing", missing);
        assertEquals(result, "register");
    }

    @Test
    public void testRegisterUserToDbShouldGiveRegisterMissingUrl() {
        //given
        given(registerForm.getName()).willReturn("");

        //when
        RedirectView redirectView = underTest.registerUserToDb(registerForm);

        //then
        verify(registerForm).getName();
        assertEquals(redirectView.getUrl(), "/register?missing=1");
    }

    @Test
    public void testRegisterUserToDBShouldGiveBackRegisterSuccessUrlWhenTeacherNotExists() {
        //given
        given(registerForm.getName()).willReturn("name");
        given(registerForm.getUsername()).willReturn("username");
        given(registerForm.getType()).willReturn("teacher");
        given(teacherServiceInterface.existsTeacherByUserName("username")).willReturn(false);

        //when
        RedirectView redirectView = underTest.registerUserToDb(registerForm);

        //then
        verify(registerForm, atLeast(2)).getName();
        verify(registerForm, atLeast(2)).getUsername();
        verify(registerForm).getType();
        verify(teacherServiceInterface).existsTeacherByUserName("username");
        assertEquals(redirectView.getUrl(), "/?registerSuccess=1");
    }

    @Test
    public void testRegisterUserToDBShouldGiveBackRegisterExistsUrlWhenTeacherExists() {
        //given
        given(registerForm.getName()).willReturn("name");
        given(registerForm.getUsername()).willReturn("username");
        given(registerForm.getType()).willReturn("teacher");
        given(teacherServiceInterface.existsTeacherByUserName("username")).willReturn(true);

        //when
        RedirectView redirectView = underTest.registerUserToDb(registerForm);

        //then
        verify(registerForm).getName();
        verify(registerForm, atLeast(2)).getUsername();
        verify(registerForm).getType();
        verify(teacherServiceInterface).existsTeacherByUserName("username");
        assertEquals(redirectView.getUrl(), "/register?exists=1");
    }

    @Test
    public void testRegisterUserToDBShouldGiveBackRegisterSuccessUrlWhenStudentNotExists() {
        //given
        given(registerForm.getName()).willReturn("name");
        given(registerForm.getUsername()).willReturn("username");
        given(registerForm.getType()).willReturn("notteacher");
        given(studentServiceInterface.existsStudentByUserName("username")).willReturn(false);

        //when
        RedirectView redirectView = underTest.registerUserToDb(registerForm);

        //then
        verify(registerForm, atLeast(2)).getName();
        verify(registerForm, atLeast(2)).getUsername();
        verify(registerForm).getType();
        verify(studentServiceInterface).existsStudentByUserName("username");
        assertEquals(redirectView.getUrl(), "/?registerSuccess=1");
    }

    @Test
    public void testRegisterUserToDBShouldGiveBackRegisterExistsUrlWhenStudentExists() {
        //given
        given(registerForm.getName()).willReturn("name");
        given(registerForm.getUsername()).willReturn("username");
        given(registerForm.getType()).willReturn("notteacher");
        given(studentServiceInterface.existsStudentByUserName("username")).willReturn(true);

        //when
        RedirectView redirectView = underTest.registerUserToDb(registerForm);

        //then
        verify(registerForm).getName();
        verify(registerForm, atLeast(2)).getUsername();
        verify(registerForm).getType();
        verify(studentServiceInterface).existsStudentByUserName("username");
        assertEquals(redirectView.getUrl(), "/register?exists=1");
    }

}