package com.antoniovinter.implementation.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String bio;
    private int age;

    @OneToOne(mappedBy = "profile")
    private User user;

    public Profile(){}

    public Profile(String bio, int age) {
        this.bio = bio;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return id == profile.id &&
                age == profile.age &&
                Objects.equals(bio, profile.bio) &&
                Objects.equals(user, profile.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bio, age, user);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", bio='" + bio + '\'' +
                ", age=" + age +
                ", user=" + user +
                '}';
    }
}
