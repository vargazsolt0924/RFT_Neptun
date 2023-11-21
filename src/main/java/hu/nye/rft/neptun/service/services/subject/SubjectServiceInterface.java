package hu.nye.rft.neptun.service.services.subject;

import hu.nye.rft.neptun.model.Entities.Subject;
import hu.nye.rft.neptun.service.exceptions.UserNotFoundException;

import java.util.Collection;

public interface SubjectServiceInterface {
    void addSubject(Subject request);
    Collection<Subject> getAllSubject();
    Subject getSubjectBySubjectId(Long id) throws UserNotFoundException;
    Collection<Subject> getAllSubjectByTeacherId(Long teacherId);
    void deleteByIdOnlySubject(Long id);
    Collection<Subject> getAllSubjectThatHasStudentId(Long id);
    Collection<Subject> getAllSubjectThatDoesNotHaveStudentId(Long id);
}
