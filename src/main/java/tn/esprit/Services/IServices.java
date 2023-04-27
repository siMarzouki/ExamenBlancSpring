package tn.esprit.Services;

import tn.esprit.DAO.Entities.Declaration;
import tn.esprit.DAO.Entities.Utilisateur;

import java.util.List;

public interface IServices {
    Utilisateur ajouterVictime(Utilisateur victime);
    String ajouterPoliciers(List<Utilisateur> policiers);
    String ajouterDeclarationEtAffecterAVictime(Declaration declaration,long telephone);
    void affecterPolicierADeclaration(long idUtilisateur,long idDeclaration);
    void traiterDeclarationAutomatiquement();
    List<Utilisateur> afficherDeclarationTraites();

}

