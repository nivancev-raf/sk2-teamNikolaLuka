package nikolalukatrening.Zakazivanje_servis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startTime;
    private Date date;
    private String trainingType;
    private Boolean isGroupTraining;
    private Integer maxParticipants;

    // Dodajte ostale potrebne atribute

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;
}