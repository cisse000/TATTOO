package com.example.demo.controller;
import com.example.demo.Exception.NotFoundException;
import com.example.demo.Service.TatoueurService;
import com.example.demo.model.Tatoueur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
//import java.util.Arrays;
import java.util.List;

//Nous aurons bien sûr dès le départ notre TatoueurController qui aura pour
//annotation que c’est une API Rest
@RestController
@CrossOrigin
public class TatoueurController {


    // Notre controller contiendra désormais un attribut Service :
    @Autowired
    private TatoueurService tatoueurService;

    @GetMapping("/tatoueurs")

    public List<Tatoueur> getTatoueurs(@RequestParam(required = false) String style) {
        return tatoueurService.getTatoueurs(style);
    }


//va dans supabase et rajoute ta table tatoueur

    // Create :
    @PostMapping("/tatoueurs")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Tatoueur ajouterTatoueur(@RequestBody Tatoueur nouveauTatoueur) {
        return this.tatoueurService.ajouterTatoueur(nouveauTatoueur);
    }


    @GetMapping("/tatoueurs/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Tatoueur afficherTatoueur(@PathVariable int id) throws NotFoundException {
        return this.tatoueurService.afficherTatoueur(id);
    }


    //Update :
    @PutMapping("/tatoueurs/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Tatoueur modifierTatoueur(@RequestBody Tatoueur tatoueurAModif, @PathVariable int id) throws NotFoundException {
        return this.tatoueurService.modifierTatoueur(id,tatoueurAModif);
    }

        //Delete :
        @DeleteMapping("/tatoueurs/{id}")
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        public void supprimerTatoueur ( @PathVariable int id) throws  NotFoundException {
            this.tatoueurService.supprimerTatoueur(id);
        }

    }












/*
    //l’annotation RequestBody récupère le corps de la
//requête http et la transforme en objet java
    @PostMapping("/tatoueurs")
    public List<Tatoueur> ajouterTatoueur(@RequestBody Tatoueur nouveauTatoueur) {
        this.listeTatoueurs.add(nouveauTatoueur);
        return listeTatoueurs;

    }

    @GetMapping("/tatoueurs/{id}")
    public Tatoueur afficherTatoueur(@PathVariable int id) {
//Cherchez le tatoueur ou la tatoueuse dans votre liste et retourner l’objet trouvé.
        //faire un foreach sur listeTatoueurs
        //pour chaque Tatoueur dans chaque case , vérifiez di getId==i//si oui , retrouve  le tatoueur
        for (Tatoueur t : this.listeTatoueurs) {
            if (t.getId() == id) {
                // return this.listeTatoueurs.get(id);
                return t;
            }
        }
        return null;
    }


    @PutMapping("/tatoueurs/{id}")
    public void modifierTatoueur(@RequestBody Tatoueur tatoueurAModif, @PathVariable int id) {
//Partie à écrire pour modifier le tatouer a modifier
        //solution 1
        for (Tatoueur t : this.listeTatoueurs) {
            if(t.getId() == id);{
                t.setNom(tatoueurAModif.getNom());
                t.setStyle(tatoueurAModif.getStyle());
            }
        }
    }

        //solution2
       for (int i = 0; i < this.listeTatoueurs.size(); i++) {
            if (this.listeTatoueurs.get(i).getId() == id) {
                this.listeTatoueurs.set(i, tatoueurAModif);
            }
        }
    }


    @DeleteMapping("/tatoueur/id")
    public void supprimerTatoueur(@PathVariable int id) {
        for (int i = 0; i < this.listeTatoueurs.size(); i++) {
            if (this.listeTatoueurs.get(i).getId() == id) {
                this.listeTatoueurs.remove(i);
            }
        }
    }


    @DeleteMapping("/tatoueur")
    public void supprimerTatoueur() {
        //supprimer tous
        this.listeTatoueurs.clear();


    }


}

*/