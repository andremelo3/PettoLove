package org.academiadecodigo.pettolove.persistence.model;

import javax.persistence.*;

@Entity
@Table(name = "animal")
public class Animal extends AbstractModel {

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    private String name;

    private String description;

    private String breed;

    @OneToOne
    @JoinColumn(name = "animalType")
    private AnimalType animalType;

    private Integer likes;

    private String gender;

    private String age;

    private String imageLink;

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }


}
