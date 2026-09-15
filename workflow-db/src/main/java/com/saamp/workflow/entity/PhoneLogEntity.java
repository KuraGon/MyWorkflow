package com.saamp.workflow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "phone_logs")
@Getter
@Setter
public class PhoneLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forfait_contact_id", foreignKey = @ForeignKey(name = "fk_phone_logs_forfait_contact"))
    private PhoneContactEntity forfaitContact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caller_contact_id", foreignKey = @ForeignKey(name = "fk_phone_logs_caller_contact"))
    private PhoneContactEntity callerContact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_contact_id", foreignKey = @ForeignKey(name = "fk_phone_logs_destination_contact"))
    private PhoneContactEntity destinationContact;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @Column(name = "type", length = 20)
    private String type;

    @Column(name = "zone_client", length = 50)
    private String zoneClient;

    @Column(name = "call_type", length = 50)
    private String callType;

    @Column(name = "direction", length = 10)
    private String direction;

    @Column(name = "duration_sec")
    private Integer durationSec;

    @Column(name = "quantity_octets")
    private BigDecimal quantityOctets;

    @Column(name = "cost_ht", precision = 10, scale = 2)
    private BigDecimal costHt;
}
