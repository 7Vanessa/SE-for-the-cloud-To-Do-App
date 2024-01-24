package com.example.todoappauthservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Column(name = "email")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "roles")
    private String roles;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getRoles(){
        return roles;
    }

    public void setRoles(String roles){
        this.roles = roles;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }

    @Override
    @Transactional
    public String toString(){
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password=<hashed password>" +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
