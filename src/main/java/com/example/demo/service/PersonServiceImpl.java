package com.example.demo.service;

import com.example.demo.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private static final String JSON_FILE_PATH = "C:\\Users\\MOHAB\\Desktop\\SpringCodeExample\\src\\main\\resources\\UserData.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Person> loadPersons() {
        try {
            File file = new File(JSON_FILE_PATH);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<Person>>() {});
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    private void savePersons(List<Person> persons) {
        try {
            objectMapper.writeValue(new File(JSON_FILE_PATH), persons);
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }

    @Override
    public Boolean addPerson(Person p) {
        List<Person> persons = loadPersons();
        if (persons.stream().anyMatch(person -> person.getId() == p.getId())) {
            return false;
        }
        persons.add(p);
        savePersons(persons);
        return true;
    }

    @Override
    public Boolean deletePerson(int id) {
        List<Person> persons = loadPersons();
        boolean removed = persons.removeIf(person -> person.getId() == id);
        if (removed) {
            savePersons(persons);
        }
        return removed;
    }

    @Override
    public Person getPerson(int id) {
        List<Person> persons = loadPersons();
        return persons.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Person[] getAllPersons() {
        List<Person> persons = loadPersons();
        return persons.toArray(new Person[0]);
    }

    @Override
    public Boolean updatePerson(int id, String newName) {
        List<Person> persons = loadPersons();
        for (Person person : persons) {
            if (person.getId() == id) {
                person.setName(newName);
                savePersons(persons);
                return true;
            }
        }
        return false;
    }
}
