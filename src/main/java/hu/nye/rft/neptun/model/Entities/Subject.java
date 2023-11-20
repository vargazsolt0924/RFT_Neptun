package hu.nye.rft.neptun.model.Entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subject")
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long subjectId;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "dayOfTheWeek", nullable = false)
    public String dayOfTheWeek;

    @Column(name = "startTime", nullable = false)
    public String startTime;

    @Column(name = "durationInMinutes", nullable = false)
    public int durationInMinutes;

    @Column(name = "maxStudent", nullable = false)
    public int maxStudent;

    @Column(name = "kredit", nullable = false)
    public int kredit;

    private final Set<Student> students = new HashSet<>();;

    public Subject(Long subjectId, String name, String dayOfTheWeek, String startTime, int durationInMinutes, int maxStudent, int kredit) {
        this.subjectId = subjectId;
        this.name = name;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
        this.maxStudent = maxStudent;
        this.kredit = kredit;
    }

    public void addStudent(Student student) {
        this.students.add(student);
        student.getSubjects().add(this);
    }

    public void removeStudent(long studentId) {
        Student student = this.students.stream().filter(t -> t.getStudentId() == studentId).findFirst().orElse(null);
        if (student != null) {
            this.students.remove(student);
            student.getSubjects().remove(this);
        }
    }

}
