package com.antoniovinter.api.dto;

import java.util.Objects;

public class ProfileDto {


    private String bio;
    private int age;
    private UserDto user;

    public ProfileDto(){}

    public ProfileDto(String bio, int age, UserDto user) {
        this.bio = bio;
        this.age = age;
        this.user = user;
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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDto that = (ProfileDto) o;
        return age == that.age &&
                Objects.equals(bio, that.bio) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bio, age, user);
    }

    @Override
    public String toString() {
        return "ProfileDto{" +
                ", bio='" + bio + '\'' +
                ", age=" + age +
                ", user=" + user +
                '}';
    }
}
