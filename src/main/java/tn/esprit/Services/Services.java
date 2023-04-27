package tn.esprit.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.DAO.Entities.Declaration;
import tn.esprit.DAO.Entities.Role;
import tn.esprit.DAO.Entities.Utilisateur;
import tn.esprit.DAO.Repositories.DeclarationRepo;
import tn.esprit.DAO.Repositories.ProprieteRepo;
import tn.esprit.DAO.Repositories.UtilisateurRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class Services implements IServices {
    UtilisateurRepo utilisateurRepo;
    DeclarationRepo declarationRepo;
    ProprieteRepo proprieteRepo;

    @Override
    public Utilisateur ajouterVictime(Utilisateur victime) {
        if (victime.getRole().equals(Role.POLICIER))return new Utilisateur();
        return utilisateurRepo.save(victime);
    }

    @Override
    public String ajouterPoliciers(List<Utilisateur> policiers) {
        int count=0;
        for (int i=0;i<policiers.size();i++){
            if (policiers.get(i).getRole().equals(Role.POLICIER)){
                count++;
                utilisateurRepo.save(policiers.get(i));
            }
        }
        return count+" policiers sont ajoutes avec succés !";
    }

    @Override
    public String ajouterDeclarationEtAffecterAVictime(Declaration declaration, long telephone) {
        Utilisateur u = utilisateurRepo.findByTelephone(telephone);
        declaration.setVictime(u);
        proprieteRepo.save(declaration.getPropriete());
        declarationRepo.save(declaration);
        return declaration.getId()+" est affecte a "+u.getNom();

    }

    @Override
    public void affecterPolicierADeclaration(long idUtilisateur, long idDeclaration) {
        Utilisateur p = utilisateurRepo.findById(idUtilisateur).get();
        Declaration declaration = declarationRepo.findById(idDeclaration).get();
        declaration.setPolicier(p);
        declarationRepo.save(declaration);

    }

    @Override
    public void traiterDeclarationAutomatiquement() {
        List<Declaration> decs=declarationRepo.findAll();
        for (int i=0;i<decs.size();i++){
            Date now= new Date();
            long difference = now.getTime() - decs.get(i).getDateDeclaration().getTime();
            long jours = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
            if (jours>=30){
                decs.get(i).setDateTraitement(now);
                decs.get(i).setEstTraitee(true);
                declarationRepo.save(decs.get(i));
            }

        }

    }

    @Override
    public List<Utilisateur> afficherDeclarationTraites() {
        List<Utilisateur> policiers = utilisateurRepo.findAll();
        List<Utilisateur> res= new ArrayList<>();
        for (int i=0;i<policiers.size();i++){
            if(policiers.get(i).getDeclarationsPolicier().size()>0){
                res.add(policiers.get(i));
            }

        }
        return res;
    }
}
