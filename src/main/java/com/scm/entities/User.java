package com.scm.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name="user")
@Table(name="users") // agar hum @Table annotation use nhi karenge toh ye by-default user le lega table ka name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User implements UserDetails {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId; // I have change userId to id here

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Getter(AccessLevel.NONE)
    private String password;

    @Column(length = 1000 )
    private String about;

    @Column(length = 1000)
    private String profilePic;

    private String phoneNumber;


    // information

    @Getter(AccessLevel.NONE)
    private boolean enabled = true;

    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    // http://localhost:8081/auth/emailtoken } -> email
    private String emailToken;

    @Enumerated(value = EnumType.STRING) // mtlb ye jo enum hai uska type jo save karna hai vo as a string save karna hai, ya fir google hai toh hamari string google save ho dackend main (database main )
    // SELF, GOOGLE, FACEBOOK, TWITTER, LINKEDIN, GITHUB
    private Providers provider = Providers.SELF;
    private String providerUserId;

    // add more field if needed

    // map contacts to user
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();



    // ----------------------  user details


    // ye method jab kkam aayega jab hum authority ki baat karenge, jab hum role ki baat karenge ki user k pass konsa role hai

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // list of roles[USER, ADMIN]
        // collection of SimpleGrantedAuthority[roles{ADMIN,USER}]
        List<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    // for this project : jo hamari email id hai vahi username hai
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // true = not expire
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled(){
        return this.enabled;
    }



}

