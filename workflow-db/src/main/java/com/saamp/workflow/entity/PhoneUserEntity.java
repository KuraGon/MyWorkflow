package com.saamp.workflow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "phone_user")
@Getter
@Setter
@ToString(exclude = {"phonePro", "phonePerso", "phoneTelavox"})
public class PhoneUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;

    // Identité
    @Column(name = "firstname", length = 120)
    private String firstname;

    @Column(name = "lastname", length = 120)
    private String lastname;

    // Personne morale
    @Column(name = "denomination", length = 255)
    private String denomination;

    @Column(name = "company", length = 255)
    private String company;

    @Column(name = "mysaamp_id_client")
    private Long mysaampIdClient;

    // 3 "numéros" = liens vers phone_contact
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_pro_id")
    private PhoneContactEntity phonePro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_perso_id")
    private PhoneContactEntity phonePerso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_telavox_id")
    private PhoneContactEntity phoneTelavox;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_other_id")
    private PhoneContactEntity phoneOther;
}
