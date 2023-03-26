package com.seniorproject.first.prototype.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // mb will delete
public class Participation {
    @Id
    @SequenceGenerator(
            name = "participationId_sequence",
            sequenceName = "participationId_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "participationId_sequence"
    )
    private Long participationId;
    private String status;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "participant_id",
            referencedColumnName = "userId"
    )
    private User participant;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    //@JsonIgnore
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "experiment_id",
            referencedColumnName = "experimentId"
    )
    private Experiment experiment;

    private String participantResults;

    //@JsonIgnore
    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    //@JsonIgnore
    public Experiment getExperiment() {
        return this.experiment;
    }
}