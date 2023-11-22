package hu.nye.rft.neptun.service.repositories;

import hu.nye.rft.neptun.model.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
        @Query(value = "SELECT HALLGATO_ID FROM HALLGATO WHERE USERNAME = ?1", nativeQuery = true)
        Long findIdByUserName(String userName);

        boolean existsStudentByUserName(String userName);
}
