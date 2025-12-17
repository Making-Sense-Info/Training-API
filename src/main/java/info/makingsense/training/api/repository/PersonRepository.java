package info.makingsense.training.api.repository;

import info.makingsense.training.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    default Optional<Person> updatePerson(Long id, Person updatedPerson) {
        return findById(id)
                .map(person -> {
                    person.setName(updatedPerson.getName());
                    person.setHeight(updatedPerson.getHeight());
                    person.setMass(updatedPerson.getMass());
                    person.setHairColor(updatedPerson.getHairColor());
                    person.setSkinColor(updatedPerson.getSkinColor());
                    person.setEyeColor(updatedPerson.getEyeColor());
                    person.setBirthYear(updatedPerson.getBirthYear());
                    person.setGender(updatedPerson.getGender());
                    return save(person);
                });
    }
}

