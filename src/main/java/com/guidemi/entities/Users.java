package com.guidemi.entities;

import javax.persistence.*;


@Entity
@Table(name = "user_data")
public class Users {

    @Id
    int id;

    @Column(nullable = false)
    String name;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    String email;

    @Column
    String picture;

    @Column
    String currentLocation;

    @OneToMany
    Users friend;


    public Users(int id, String name, String firstName, String lastName, String email,
                 String picture, String currentLocation, Users friend) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.picture = picture;
        this.currentLocation = currentLocation;
        this.friend = friend;
    }
}
