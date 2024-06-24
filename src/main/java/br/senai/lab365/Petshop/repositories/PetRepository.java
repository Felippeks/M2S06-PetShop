// PetRepository.java
package br.senai.lab365.Petshop.repositories;

import br.senai.lab365.Petshop.models.Pet;
import br.senai.lab365.Petshop.models.Tutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PetRepository {

    private static List<Pet> pets = new ArrayList<>();
    private static int ultimoId;

    public List<Pet> listar() {
        return pets;
    }

    public void adicionar(Pet pet) {
        pet.setId(++ultimoId);
        pets.add(pet);
    }

    public void remover(Pet pet) {
        pets.remove(pet);
    }

    public Pet buscar(int id) {
        return pets.stream().filter(pet -> pet.getId() == id).findFirst().orElse(null);
    }

    public void atualizarTutor(int idPet, Tutor tutor) {
        Pet pet = buscar(idPet);
        if (pet != null) {
            pet.setTutor(tutor);
        }
    }
}