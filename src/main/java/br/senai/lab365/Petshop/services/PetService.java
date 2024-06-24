// PetService.java
package br.senai.lab365.Petshop.services;

import br.senai.lab365.Petshop.models.Pet;
import br.senai.lab365.Petshop.models.Tutor;
import br.senai.lab365.Petshop.repositories.PetRepository;
import br.senai.lab365.Petshop.repositories.TutorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {
    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;

    public PetService(PetRepository petRepository, TutorRepository tutorRepository) {
        this.petRepository = petRepository;
        this.tutorRepository = tutorRepository;
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

    public void atualizarTutor(int idPet, int idTutor) {
        Pet pet = petRepository.buscar(idPet);
        Tutor tutor = tutorRepository.buscar(idTutor);
        if (pet != null && tutor != null) {
            petRepository.atualizarTutor(idPet, tutor);
        }
    }


    public List<Pet> buscarPetsPorTutor(int idTutor) {
        Tutor tutor = tutorRepository.buscar(idTutor);
        if (tutor != null) {
            return petRepository.listar().stream().filter(pet -> pet.getTutor().equals(tutor)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}