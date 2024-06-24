package br.senai.lab365.Petshop.controllers;

import br.senai.lab365.Petshop.models.Pet;
import br.senai.lab365.Petshop.models.Tutor;
import br.senai.lab365.Petshop.services.PetService;
import br.senai.lab365.Petshop.services.TutorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private PetService petService;
    private TutorService tutorService;

    public PetController(PetService petService, TutorService tutorService) {
        this.petService = petService;
        this.tutorService = tutorService;
    }

    @PostMapping
    public void cadastrar(@RequestBody Pet pet) {
        if (pet.getTutor() != null && pet.getTutor().getId() != 0) {
            Tutor tutor = tutorService.buscar(pet.getTutor().getId());
            if (tutor != null) {
                pet.setTutor(tutor);
            } else {
                throw new RuntimeException("404");
            }
        } else {
            pet.setTutor(null);
        }
        petService.cadastrar(pet);
    }

    @GetMapping
    public List<Pet> listar() {
        var pets = petService.listar();
        if (pets.isEmpty()) {
            throw new RuntimeException("404");
        } else {
            return pets;
        }
    }

    @GetMapping("/{id}")
    public Pet buscar(@PathVariable int id) {
        Pet pet = petService.buscar(id);
        if (pet != null) {
            return pet;
        } else {
            throw new RuntimeException("404");
        }
    }

    @DeleteMapping("/{id}")
    public String remover(@PathVariable int id) {
        return petService.remover(id) ? "Pet removido" : String.format("Pet com id %d não encontrado", id);

    }
    @PutMapping("/{id}")
    public Pet atualizar(@PathVariable int id, @RequestBody Pet petAtualizado) {
        Pet pet = petService.atualizar(id, petAtualizado);
        if (pet != null) {
            return pet;
        } else {
            throw new RuntimeException("404");
        }
    }
}