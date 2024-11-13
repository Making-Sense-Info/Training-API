package info.makingsense.training.api.controller;

import info.makingsense.training.api.model.Person;
import info.makingsense.training.api.repository.PersonRepository;
import info.makingsense.training.api.service.DataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
@Tag(name = "Person Controller", description = "CRUD operations for people")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DataService dataService;

    @GetMapping
    @Operation(summary = "Get all people", description = "Retrieve a list of all people in the database")
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get person by ID", description = "Retrieve a specific person by their ID")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return personRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Add a new person", description = "Create a new person in the database")
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete person by ID", description = "Delete a specific person by their ID")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/reset")
    @Operation(summary = "Reset database", description = "Clear the database and repopulate it with initial data from SWAPI")
    public ResponseEntity<Void> resetDatabase() {
        personRepository.deleteAll();
        dataService.importData();
        return ResponseEntity.noContent().build();
    }
}

