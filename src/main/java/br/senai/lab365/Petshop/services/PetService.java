package br.senai.lab365.Petshop.services;

import br.senai.lab365.Petshop.models.Pet;
import br.senai.lab365.Petshop.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void cadastrar(Pet pet) {
        petRepository.adicionar(pet);
    }

    public List<Pet> listar() {
        return petRepository.listar();
    }

    public Pet buscar(int id) {
        return petRepository.buscar(id);
    }

    public boolean remover(int id) {
        var petExcluir = petRepository.buscar(id);
        if (petExcluir != null) {
            petRepository.remover(petExcluir);
            return true;
        }
        return false;
        }

    public Pet atualizar(int id, Pet petAtualizado) {
        Pet petExistente = petRepository.buscar(id);
        if (petExistente != null) {
            petAtualizado.setId(petExistente.getId());
            petRepository.remover(petExistente);
            petRepository.adicionar(petAtualizado);
            return petAtualizado;
        }
        return null;
    }
    }
