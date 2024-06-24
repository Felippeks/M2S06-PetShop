// TutorController.java
package br.senai.lab365.Petshop.controllers;

import br.senai.lab365.Petshop.models.Pet;
import br.senai.lab365.Petshop.models.Tutor;
import br.senai.lab365.Petshop.services.PetService;
import br.senai.lab365.Petshop.services.TutorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    private TutorService tutorService;
    private PetService petService;

    public TutorController(TutorService tutorService, PetService petService) {
        this.tutorService = tutorService;
        this.petService = petService;
    }

    @PostMapping
    public void cadastrar(@RequestBody Tutor tutor) {
        tutorService.cadastrar(tutor);
    }

    @GetMapping
    public List<Tutor> listar() {
        var tutores = tutorService.listar();
        if (tutores.isEmpty()) {
            throw new RuntimeException("404");
        } else {
            return tutores;
        }
    }

    @GetMapping("/{id}")
    public Tutor buscar(@PathVariable int id) {
        Tutor tutor = tutorService.buscar(id);
        if (tutor != null) {
            return tutor;
        } else {
            throw new RuntimeException("404");
        }
    }

    @DeleteMapping("/{id}")
    public String remover(@PathVariable int id) {
        return tutorService.remover(id) ? "Tutor removido" : String.format("Tutor com id %d não encontrado", id);
    }

    @PutMapping("/{id}")
    public Tutor atualizar(@PathVariable int id, @RequestBody Tutor tutorAtualizado) {
        Tutor tutor = tutorService.atualizar(id, tutorAtualizado);
        if (tutor != null) {
            return tutor;
        } else {
            throw new RuntimeException("404");
        }
    }

    @GetMapping("/{idTutor}/pets")
    public List<Pet> buscarPetsPorTutor(@PathVariable int idTutor) {
        return petService.buscarPetsPorTutor(idTutor);
    }
    @PutMapping("/{idPet}/tutor/{idTutor}")
    public void atualizarTutor(@PathVariable int idPet, @PathVariable int idTutor) {
        petService.atualizarTutor(idPet, idTutor);
    }
}