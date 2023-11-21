package hu.nye.rft.neptun.model.entities;


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
}
