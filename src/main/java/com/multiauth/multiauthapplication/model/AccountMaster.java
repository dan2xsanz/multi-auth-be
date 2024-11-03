package com.multiauth.multiauthapplication.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
=======
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
>>>>>>> 672fd2c7dd511a2e4a038241324e41a9a74203eb
import java.util.Set;

import static org.springframework.jdbc.object.BatchSqlUpdate.DEFAULT_BATCH_SIZE;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AccountMaster")
public class AccountMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("profileImg")
    private String profileImg;

    @JsonProperty("coverImg")
    private String coverImg;

    private String email;

    private String password;

    @OneToMany(mappedBy = "accountMaster",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            }, fetch = FetchType.LAZY)
    @JsonBackReference
    @BatchSize(size = DEFAULT_BATCH_SIZE)
    private Set<CommentSectionMaster> commentSections;
}
