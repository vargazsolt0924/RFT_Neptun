package hu.nye.rft.neptun.service.services.student;

import hu.nye.rft.neptun.model.Entities.Student;
import hu.nye.rft.neptun.service.exceptions.UserNotFoundException;
import hu.nye.rft.neptun.service.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService implements StudentServiceInterface {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void addStudent(Student student) {
        try{
            studentRepository.save(student);
        }catch (Exception e){
            throw new RuntimeException("Oops, something went wrong.");
        }
    }

    @Override
    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) throws UserNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public Long getStudentIdByUsername(String userName) {
        return studentRepository.findIdByUserName(userName);
    }

    @Override
    public boolean existsStudentByUserName(String userName) {
        return studentRepository.existsStudentByUserName(userName);
    }

    @Override
    public void removeStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}
