package hu.nye.rft.neptun.model.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "studnet")
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long studentId;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "userName", nullable = false)
    public String userName;

    @Getter
    @JsonIgnore
    public Set<Subject> subjects = new HashSet<>();

    public Student(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }
}
