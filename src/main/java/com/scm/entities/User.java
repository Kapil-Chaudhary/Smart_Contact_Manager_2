package com.scm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="user")
@Table(name="users") // agar hum @Table annotation use nhi karenge toh ye by-default user le lega table ka name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {

    @Id
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(length = 1000 )
    private String about;

    @Column(length = 1000)
    private String profilePic;

    private String phoneNumber;


    // information
    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;


    @Enumerated
    // SELF, GOOGLE, FACEBOOK, TWITTER, LINKEDIN, GITHUB
    private Providers provider = Providers.SELF;
    private Providers providerUserId;

    // add more field if needed

    // map contacts to user
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

}
