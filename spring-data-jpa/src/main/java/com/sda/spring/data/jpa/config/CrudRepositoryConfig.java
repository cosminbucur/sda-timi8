package com.sda.spring.data.jpa.config;

import com.sda.spring.data.jpa.model.Person;
import com.sda.spring.data.jpa.repository.PersonCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

//@Configuration
public class CrudRepositoryConfig {

    // add logging
    private static final Logger logger = LoggerFactory.getLogger(CrudRepositoryConfig.class);

    // test methods from person crud repository
    @Autowired
    public PersonCrudRepository repository;

    // runs after application starts
    @Bean
    public CommandLineRunner crudData() {
        return args -> testCrudMethods();
    }

    private void testCrudMethods() {
        // create
        repository.save(new Person("ana", 25));
        repository.save(new Person("paul", 30));

        // read
        // find paul by name
        // name must be a variable on class person
        Person paul = repository.findByName("paul")
            .orElseThrow(() -> new RuntimeException("person not found"));
        logger.info("found person: {}", paul);

        // find all persons and print them
        repository.findAll()
            .forEach(person -> logger.info("person: {} - {}", person, person.getId()));

        boolean existsById = repository.existsById(paul.getId());
        logger.info("{} exists by id: {}", paul.getName(), existsById);

        // update
        paul.setAge(36);
        repository.save(paul);
        logger.info("updated person: {}", paul);

        // count before
        logger.info("before delete we have {} persons", repository.count());
        // delete
        repository.deleteById(paul.getId());
        // count after
        logger.info("after delete we have {} persons", repository.count());

        repository.deleteAll();
    }

    private void sampleSupplier() {
        // supplier
        // (no input) -> { return something }
        Supplier<String> stringSupplier = () -> {
            return "hello";
        };
    }

    private void sampleConsumer() {
        // consumer
        // (input) -> { do something with input }
        Consumer<String> stringConsumer = (name) -> {
            System.out.println(name);
        };
    }

    private void howNotToUseOptional() {
        Optional<Person> personOptional = repository.findByName("paul");
        if (personOptional.isPresent()) {
            Person foundPerson = personOptional.get();
            System.out.println(foundPerson);
        }
    }

    // run code after application start
    @Bean
    public CommandLineRunner testData() {
        // inputs -> body
        // new school lambda functional interface implementation
        CommandLineRunner runner = args -> {
            // do something in run method
        };

        // old school in line interface implementation
//        CommandLineRunner runner1 = new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                // do something in run method
//            }
//        };
        return runner;
    }
}
