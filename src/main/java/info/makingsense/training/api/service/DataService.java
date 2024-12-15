package info.makingsense.training.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.makingsense.training.api.model.Person;
import info.makingsense.training.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class DataService {

    private static final String LOCAL_JSON_FILE = "data.json";

    @Autowired
    private PersonRepository personRepository;

    public void importData() {
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(LOCAL_JSON_FILE)) {
            if (inputStream == null) {
                throw new IOException("Could not find file: " + LOCAL_JSON_FILE);
            }

            // Parse the JSON file as a List
            List<Map<String, String>> people = objectMapper.readValue(inputStream, List.class);

            // Process and save each person
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

