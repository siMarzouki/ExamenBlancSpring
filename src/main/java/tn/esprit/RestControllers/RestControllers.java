package tn.esprit.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.Entities.Declaration;
import tn.esprit.DAO.Entities.Utilisateur;
import tn.esprit.Services.IServices;

import java.util.List;

@RestController
@AllArgsConstructor
public class RestControllers {
    @Autowired
    IServices iServices;
    @PostMapping("/ajouterVictime")
    public Utilisateur ajouterVictime(@RequestBody Utilisateur vicitme) {
        return iServices.ajouterVictime(vicitme);
    }
    @PostMapping("/ajouterPoliciers")
    public String ajouterPoliciers(@RequestBody List<Utilisateur> policiers) {
        return iServices.ajouterPoliciers(policiers);
    }
    @PostMapping("/ajouterDeclarationEtAffecterAVictime")
    public String ajouterDeclarationEtAffecterAVictime(@RequestBody Declaration declaration, @RequestParam long telephone) {
        return iServices.ajouterDeclarationEtAffecterAVictime(declaration,telephone);
    }
    @PostMapping("/affecterPolicierADeclaration")
    public void affecterPolicierADeclaration(@RequestParam long idUtilisateur,@RequestParam long idDeclaration) {
         iServices.affecterPolicierADeclaration(idUtilisateur,idDeclaration);
    }
    @GetMapping("/traiterDeclarationAutomatiquement")
    public void traiterDeclarationAutomatiquement() {
        iServices.traiterDeclarationAutomatiquement();
    }

    @GetMapping("/afficherDeclarationTraites")
    public List<Utilisateur> afficherDeclarationTraites() {
        return iServices.afficherDeclarationTraites();
    }




}