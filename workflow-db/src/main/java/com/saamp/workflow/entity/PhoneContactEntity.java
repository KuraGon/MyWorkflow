package com.saamp.workflow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(
        name = "phone_contact",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_phone_contact_phone_number", columnNames = "phone_number")
        }
)
@Getter
@Setter
@ToString
public class PhoneContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "phone_number", nullable = false, length = 30)
    private String phoneNumber;
}
