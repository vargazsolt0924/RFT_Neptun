package hu.nye.rft.neptun.service.repositories;

import hu.nye.rft.neptun.model.Entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query(value = "SELECT * FROM TANTARGY WHERE TANAR_ID = ?1", nativeQuery = true)
    Collection<Subject> getAllSubjectByTeacherId(Long teacherId);

    @Modifying
    @Query(value = "DELETE FROM TANTARGY WHERE tantargy_Id = ?1", nativeQuery = true)
    void deleteByIdOnlySubject(Long id);

    @Query(value = "SELECT * FROM TANTARGY WHERE TANTARGY_ID IN (SELECT TANTARGY_ID FROM HALLGATO_TANTARGY_MAP WHERE HALLGATO_ID = ?1)", nativeQuery = true)
    Collection<Subject> getAllSubjectThatHasStudentId(Long id);

    @Query(value = "SELECT * FROM TANTARGY WHERE TANTARGY_ID NOT IN (SELECT TANTARGY_ID FROM HALLGATO_TANTARGY_MAP WHERE HALLGATO_ID = ?1)", nativeQuery = true)
    Collection<Subject> getAllSubjectThatDoesNotHaveStudentId(Long id);
}
