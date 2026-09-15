package com.saamp.workflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "active", nullable = false)
    private Boolean active;

    // Login Oracle (ex: JDOE), utile car l'email peut changer
    @Column(name = "username")
    private String username;

    // ID technique Oracle (ex: 1042) pour la traçabilité
    @Column(name = "legacy_id", unique = true)
    private Long legacyId;

    // ID du groupe d'appartenance (PC_COMP_ID)
    // Permet d'identifier que c'est un utilisateur et non un groupe
    @Column(name = "legacy_group_id")
    private Long legacyGroupId;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();
}