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

    @Column(name = "credit", nullable = false)
    public int credit;

    private final Set<Student> students = new HashSet<>();;

    public Subject(Long subjectId, String name, String dayOfTheWeek, String startTime, int durationInMinutes, int maxStudent, int credit) {
        this.subjectId = subjectId;
        this.name = name;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
        this.maxStudent = maxStudent;
        this.credit = credit;
    }

    public Subject(String name, String dayOfTheWeek, String startTime, int durationInMinutes, int maxHallgato, int credit, Teacher teacherById) {
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Set<Student> getStudents() {
        return students;
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
