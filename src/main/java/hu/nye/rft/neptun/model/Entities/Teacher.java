package hu.nye.rft.neptun.model.Entities;


import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
@NoArgsConstructor
public class Teacher  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long teacherId;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "username", nullable = false)
    public String userName;


    public Teacher(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
