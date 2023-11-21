package hu.nye.rft.neptun.service.services.subject;

import hu.nye.rft.neptun.model.Entities.Subject;
import hu.nye.rft.neptun.service.exceptions.UserNotFoundException;
import hu.nye.rft.neptun.service.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SubjectService implements SubjectServiceInterface{
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void addSubject(Subject subject) {
        try{
            subjectRepository.save(subject);
        } catch (Exception e){
            throw new RuntimeException("Oops, something went wrong.");
        }
    }

    @Override
    public Collection<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectBySubjectId(Long id) throws UserNotFoundException {
        return subjectRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Tantargy not found with id: " + id));
    }

    @Override
    public Collection<Subject> getAllSubjectByTeacherId(Long teacherId) {
        return subjectRepository.getAllSubjectByTeacherId(teacherId);
    }

    @Override
    public void deleteByIdOnlySubject(Long id) {
        subjectRepository.deleteByIdOnlySubject(id);
    }

    @Override
    public Collection<Subject> getAllSubjectThatHasStudentId(Long id) {
        return subjectRepository.getAllSubjectThatHasStudentId(id);
    }

    @Override
    public Collection<Subject> getAllSubjectThatDoesNotHaveStudentId(Long id) {
        return subjectRepository.getAllSubjectThatDoesNotHaveStudentId(id);
    }
}
