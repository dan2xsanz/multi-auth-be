package com.multiauth.multiauthapplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TINYINT(1)", length = 1)
    @JsonProperty("isRead")
    private boolean isRead;

    private String notificationTopic;

    @ManyToOne
    @JoinColumn(name = "productMasterId", nullable = false)
    @JsonBackReference
    private ProductMaster productMaster;

    @ManyToOne
    @JoinColumn(name = "accountMasterId", nullable = false)
    @JsonBackReference
    private AccountMaster accountMaster;
}
