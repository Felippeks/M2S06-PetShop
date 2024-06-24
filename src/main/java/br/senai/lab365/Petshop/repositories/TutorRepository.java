// TutorRepository.java
package br.senai.lab365.Petshop.repositories;

import br.senai.lab365.Petshop.models.Tutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TutorRepository {

    private static List<Tutor> tutores = new ArrayList<>();
    private static int ultimoId;

    public List<Tutor> listar() {
        return tutores;
    }

    public void adicionar(Tutor tutor) {
        tutor.setId(++ultimoId);
        tutores.add(tutor);
    }

    public void remover(Tutor tutor) {
        tutores.remove(tutor);
    }

    public Tutor buscar(int id) {
        return tutores.stream().filter(tutor -> tutor.getId() == id).findFirst().orElse(null);
    }
}