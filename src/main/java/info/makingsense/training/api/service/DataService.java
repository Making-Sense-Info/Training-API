package info.makingsense.training.api.service;

import info.makingsense.training.api.model.Person;
import info.makingsense.training.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class DataService {

    private static final String SWAPI_PEOPLE_URL = "https://swapi.dev/api/people/";

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void importData() {
        // Récupérer les données depuis SWAPI
        Map<String, Object> response = restTemplate.getForObject(SWAPI_PEOPLE_URL, Map.class);
        List<Map<String, String>> people = (List<Map<String, String>>) response.get("results");

        for (Map<String, String> personData : people) {
            Person person = new Person();
            person.setName(personData.get("name"));
            person.setHeight(personData.get("height"));
            person.setMass(personData.get("mass"));
            person.setHairColor(personData.get("hair_color"));
            person.setSkinColor(personData.get("skin_color"));
            person.setEyeColor(personData.get("eye_color"));
            person.setBirthYear(personData.get("birth_year"));
            person.setGender(personData.get("gender"));
            personRepository.save(person);
        }
    }
}

