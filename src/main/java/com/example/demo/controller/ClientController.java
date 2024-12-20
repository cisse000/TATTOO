package com.example.demo.controller;

import com.example.demo.Exception.NotFoundException;
import com.example.demo.Service.ClientService;
import com.example.demo.Service.TatoueurService;
import com.example.demo.model.Client;
import com.example.demo.model.Tatoueur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
//import java.util.Arrays;
import java.util.List;

//Nous aurons bien sûr dès le départ notre TatoueurController qui aura pour
//annotation que c’est une API Rest
@RestController
public class ClientController {


    // Notre controller contiendra désormais un attribut Service :
    @Autowired
    private ClientService clientService;

    @GetMapping("/client")

    public List<Client> getClient(@RequestParam(required = false) String style) {
        return clientService.getClient(style);
    }


//va dans supabase et rajoute ta table tatoueur

    // Create :
    @PostMapping("/client")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Client ajouterClient(@RequestBody Client nouveauClientr) {
        return this.clientService.ajouterClient(nouveauClient);
    }


    @GetMapping("/client/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Tatoueur afficherClient(@PathVariable int id) throws NotFoundException {
        return this.clientService.afficherClient(id);
    }


    //Update :
    @PutMapping("/client/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Client modifierClient(@RequestBody Client clientAModif, @PathVariable int id) throws NotFoundException {
        return this.clientService.modifierClient(id,clientAModif);
    }

    //Delete :
    @DeleteMapping("/client/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void supprimerClient ( @PathVariable int id) throws  NotFoundException {
        this.clientService.supprimerClient(id);
    }

}




