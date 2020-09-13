package com.sda.spring.data.jpa.config;

import com.sda.spring.data.jpa.model.Person;
import com.sda.spring.data.jpa.repository.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Configuration
public class JpaRepositoryConfig {

    private static final Logger logger = LoggerFactory.getLogger(JpaRepositoryConfig.class);

    @Autowired
    public PersonJpaRepository repository;

    @Bean
    public CommandLineRunner jpaData() {
        return args -> {
            loadData();
            testJpaMethods();
        };
    }

    private void testJpaMethods() {
        // get id of first person from db
        Long foundFirstId = repository.findAll()    // list of person
            .get(0)     // first person
            .getId();   // person id

//        List<Person> people = repository.findAll();
//        Person firstPerson = people.get(0);
//        Long foundPersonId = firstPerson.getId();

        Person existingPerson = repository.getOne(foundFirstId);

        Pageable firstTwoElements = PageRequest.of(0, 2);
        Page<Person> firstTwoPersons = repository.findAll(firstTwoElements);

        repository.deleteInBatch(firstTwoPersons);

        repository.deleteAllInBatch();
    }

    // TODO: move method in a utility component
    private void loadData() {
        repository.save(new Person("paul", 32));
        repository.save(new Person("alina", 28));
        repository.save(new Person("kazimir", 34));
        repository.save(new Person("lidia", 32));
        repository.save(new Person("violeta", 32));
    }
}
