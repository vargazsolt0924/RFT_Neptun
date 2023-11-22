package hu.nye.rft.neptun.service.repositories;

import hu.nye.rft.neptun.model.Entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query(value = "SELECT * FROM subject WHERE teacherId = ?1", nativeQuery = true)
    Collection<Subject> getAllSubjectByTeacherId(Long teacherId);

    @Modifying
    @Query(value = "DELETE FROM subject WHERE subjectId = ?1", nativeQuery = true)
    void deleteByIdOnlySubject(Long id);

    @Query(value = "SELECT * FROM subject WHERE subjectId IN (SELECT subjectId FROM student_subject WHERE studentId = ?1)", nativeQuery = true)
    Collection<Subject> getAllSubjectThatHasStudentId(Long id);

    @Query(value = "SELECT * FROM subject WHERE subjectId NOT IN (SELECT subjectId FROM student_subject WHERE studentId= ?1)", nativeQuery = true)
    Collection<Subject> getAllSubjectThatDoesNotHaveStudentId(Long id);
}
