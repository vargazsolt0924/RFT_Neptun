package hu.nye.rft.neptun.service.services.student;

import hu.nye.rft.neptun.model.Entities.Student;
import hu.nye.rft.neptun.service.exceptions.UserNotFoundException;

import java.util.Collection;

public interface StudentServiceInterface {
    void addStudent(Student request);
    Collection<Student> getAllStudent();
    Student getStudentById(Long id) throws UserNotFoundException;
    Long getStudentIdByUsername(String userName);
    boolean existsStudentByUserName(String userName);
    void removeStudentById(Long id);
}
