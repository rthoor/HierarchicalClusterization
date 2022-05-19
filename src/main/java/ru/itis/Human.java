package ru.itis;

import java.util.Objects;

public class Human {
    private String firstname;
    private String lastname;
    private int age;
    private int height;
    private int weight;

    public Human() {
    }

    public Human(String firstname, String lastname, int age, int height, int weight) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }

    public int getPoint(){
        return (int) Math.sqrt(Math.pow(height, 2) + Math.pow(weight, 2));
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age && height == human.height && weight == human.weight && Objects.equals(firstname, human.firstname) && Objects.equals(lastname, human.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, age, height, weight);
    }
}
