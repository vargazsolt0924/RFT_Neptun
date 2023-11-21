package hu.nye.rft.neptun.service.services.teacher;

import hu.nye.rft.neptun.model.Entities.Teacher;
import hu.nye.rft.neptun.service.exceptions.UserNotFoundException;
import hu.nye.rft.neptun.service.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TeacherService  implements TeacherServiceInterface{

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void addTeacher(Teacher teacher) {
        try{
            teacherRepository.save(teacher);
        }catch (Exception e){
            throw new RuntimeException("Oops, something went wrong.");
        }
    }

    @Override
    public Collection<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(Long id) throws UserNotFoundException {
        return teacherRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public Long getTeacherIdByUsername(String userName) {
        return teacherRepository.findIdByUserName(userName);
    }

    @Override
    public boolean existsTeacherByUserName(String userName) {
        return teacherRepository.existsTeacherByUserName(userName);
    }

    @Override
    public void removeTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }
}
