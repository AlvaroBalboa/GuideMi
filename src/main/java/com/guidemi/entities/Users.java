package com.guidemi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;


@Entity
@Table(name = "guidemi_user_data")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String login;

//    @JsonIgnore
//    @NotNull
//    @Size(min = 60, max = 60)
//    @Column(name = "password_hash",length = 60)
//    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    @Column(length = 100)
    private String email;

//    @NotNull
//    @Column(nullable = false)
//    private boolean activated = false;

    @Size(max = 256)
    @Column(name = "image_url")
    private String profilePicture;

    @Column
    private String currentLocation;

    //TODO the google API week or me
//
//    @JsonIgnore
//    @Size(max = 20)
//    @Column(name = "activation_key", length = 20)
//    private String activationKey;
//
//    @Size(max = 20)
//    @Column(name = "reset_key", length = 20)
//    private String resetKey;
//
//    @Column(name = "reset_date")
//    private ZonedDateTime resetDate = null;
//    5 day timer

    @Column
    @OneToMany
    private List<Users> friend;

    public Users() {
    }

    public Users(String name,String profilePicture){
        this.login=name;
        this.profilePicture=profilePicture;
    }
    //Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = login.toLowerCase(Locale.ENGLISH);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public boolean isActivated() {
//        return activated;
//    }
//
//    public void setActivated(boolean activated) {
//        this.activated = activated;
//    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public List<Users> getFriend() {
        return friend;
    }

    public void setFriend(List<Users> friend) {
        this.friend = friend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Users user = (Users) o;

        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                "}";
    }

    public Users(String login, String password, String firstName, String lastName, String email) {
        this.login = login;
//        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

