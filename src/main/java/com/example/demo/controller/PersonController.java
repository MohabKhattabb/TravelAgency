/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.example.demo.service.PersonServiceImpl;
import com.example.demo.model.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PersonsController")
public class PersonController {

    @Autowired
    PersonService personService = new PersonServiceImpl();

    @PostMapping("/add")
    public Response addPerson(@RequestBody Person p) {
        System.out.println("in add person" + p);
        boolean res = personService.addPerson(p);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Person Already Exists");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Person created successfully");
        return response;
    }

    @PutMapping("/update")
    public Response updatePerson(@RequestParam("id") int id, @RequestParam("newName") String name) {
        Boolean res = personService.updatePerson(id, name);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Person doesnt exist to update in the first place.");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Person updated successfully.");
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public Response deletePerson(@PathVariable("id") int id) {
        System.out.println("in delete with id:" + id);
        boolean res = personService.deletePerson(id);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Person Doesn't Exists");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Person deleted successfully");
        return response;
    }

    @GetMapping("/get/{id}")
    public Response<Person> getPerson(@PathVariable("id") int id) {
        System.out.println("in get with id:" + id);
        var person = personService.getPerson(id);
        Response<Person> response = new Response<Person>();
        if (person == null) {
            response.setStatus(false);
            response.setMessage("Person Not Found");
            response.object = null;

            return response;
        }

        response.setStatus(true);
        response.setMessage("Person Retrieved Successfully");
        response.object = person;

        return response;
    }

    @GetMapping("/get")
    public Person[] getAll() {
        System.out.println("in getAll");
        return personService.getAllPersons();
    }
    @GetMapping("/test")
    public String testEndpoint() {
        return "elperson fol ";
    }
}
