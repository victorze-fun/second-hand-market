package com.victorze.secondhandmarket.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message="El nombre es obligatorio")
    private String name;
    private String lastName;
    private String avatar;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @NotBlank(message="El correo es obligatorio")
    private String email;

    @NotBlank(message="el password es obligatorio")
    private String password;

    public User(String name, String lastName, String avatar, Date createdAt, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.avatar = avatar;
        this.createdAt = createdAt;
        this.email = email;
        this.password = password;
    }

}
