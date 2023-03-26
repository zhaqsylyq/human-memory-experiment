package com.seniorproject.first.prototype.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // mb will delete

@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class Experiment {
    @Id
    @SequenceGenerator(
            name = "experimentId_sequence",
            sequenceName = "experimentId_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "experimentId_sequence"
    )
    private Long experimentId;
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters.")
    @NotNull(message = "Please provide a name")
    private String experimentName;

    @Size(max = 500, message = "The description can't be longer than 500 characters.")
    @NotNull(message = "Please, provide a description")
    private String description;

    @NotNull(message = "Please, provide amount of delay between words")
    private Double betweenWordTime;

    @NotNull(message = "Please, provide word display time")
    private Double wordTime;

    @NotNull(message = "Please, set the isJoinable parameter")
    private Boolean isJoinable;

    @SuppressWarnings("JpaAttributeTypeInspection") // bc of ide issue
    @Type(type = "list-array")
    @Column(
        columnDefinition = "text[]"
    )
    private List<String> words;

    private Long participantCount;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Type(type = "list-array")
    @Column(
            columnDefinition = "integer[]"
    )
    private List<Integer> overallResults;

    private Long experimentType;

    //@JsonBackReference 26.03
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "creator_id",
            referencedColumnName = "userId"
    )
    private User creator;

//    @Getter(AccessLevel.NONE) 26.03
//    @Setter(AccessLevel.NONE)
    @JsonIgnore
    @OneToMany(
            mappedBy = "experiment",
            fetch = FetchType.LAZY
    )
    private List<Participation> participations;

//    @JsonIgnore
//    public List<Participation> getParticipations() {
//        return this.participations;
//    }
//    @JsonIgnore
//    public void setParticipations(List<Participation> participations) {
//        this.participations = participations;
//    }
}
