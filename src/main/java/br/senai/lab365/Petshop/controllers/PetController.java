package br.senai.lab365.Petshop.controllers;

import br.senai.lab365.Petshop.models.Pet;
import br.senai.lab365.Petshop.services.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public void cadastrar(@RequestBody Pet pet) {
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