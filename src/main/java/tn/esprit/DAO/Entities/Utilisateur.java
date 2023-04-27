package tn.esprit.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nom;
    String prenom;
    String adresse;

    Long telephone;
    @Enumerated(EnumType.STRING)
    Role role;
    @OneToMany(mappedBy = "victime")
    @JsonIgnore
    List<Declaration> declarationsVictime = new ArrayList<>();
    @OneToMany(mappedBy = "policier")
    @JsonIgnore
    List<Declaration> declarationsPolicier = new ArrayList<>();

}