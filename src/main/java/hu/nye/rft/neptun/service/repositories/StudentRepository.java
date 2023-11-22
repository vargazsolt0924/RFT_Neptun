package hu.nye.rft.neptun.service.repositories;

import hu.nye.rft.neptun.model.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
        @Query(value = "SELECT studentId FROM student WHERE userName = ?1", nativeQuery = true)
        Long findIdByUserName(String userName);

        boolean existsStudentByUserName(String userName);
}
