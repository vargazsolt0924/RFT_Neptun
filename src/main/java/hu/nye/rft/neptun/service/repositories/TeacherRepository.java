package hu.nye.rft.neptun.service.repositories;

import hu.nye.rft.neptun.model.Entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query(value = "SELECT TANAR_ID FROM TANAR WHERE USERNAME = ?1", nativeQuery = true)
    Long findIdByUserName(String userName);

    boolean existsTeacherByUserName(String userName);
}
