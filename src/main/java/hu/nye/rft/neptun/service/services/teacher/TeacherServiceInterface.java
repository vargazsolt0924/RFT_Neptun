package hu.nye.rft.neptun.service.services.teacher;

import hu.nye.rft.neptun.model.Entities.Teacher;
import hu.nye.rft.neptun.service.exceptions.UserNotFoundException;

import java.util.Collection;

public interface TeacherServiceInterface {
    void addTeacher(Teacher request);
    Collection<Teacher> getAllTeacher();
    Teacher getTeacherById(Long id) throws UserNotFoundException;
    Long getTeacherIdByUsername(String userName);
    boolean existsTeacherByUserName(String userName);
    void removeTeacherById(Long id);
}
