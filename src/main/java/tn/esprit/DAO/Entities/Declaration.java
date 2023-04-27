package tn.esprit.DAO.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Declaration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Temporal(TemporalType.DATE)
    Date dateDeclaration;

    boolean estTraitee;

    @Temporal(TemporalType.DATE)
    Date dateTraitement;

    String description;

    @OneToOne
    Propriete propriete;

    @ManyToOne
            @JsonIgnore
    Utilisateur victime;
    @ManyToOne
            @JsonIgnore
    Utilisateur policier;

}
