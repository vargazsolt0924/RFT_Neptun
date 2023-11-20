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
    private Long studentId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Getter
    @JsonIgnore
    private Set<Subject> subjects = new HashSet<>();

    public Student(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }



}
