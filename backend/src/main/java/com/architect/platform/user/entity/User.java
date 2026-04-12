package com.architect.platform.user.entity;

import com.architect.platform.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "first_name", nullable = false , length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false , length = 100)
    private String lastName;

    @Column(name = "email", nullable = false , unique = true , length = 150)
    private String email;


    @Column(name = "password_hash", nullable = false , length = 255)
    private String passwordHash;

    @Column(name = "phone", length = 30)
    private String phone;


    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
