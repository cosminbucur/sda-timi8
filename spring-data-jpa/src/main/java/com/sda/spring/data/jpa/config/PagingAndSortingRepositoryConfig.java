package com.sda.spring.data.jpa.config;

import com.sda.spring.data.jpa.model.Person;
import com.sda.spring.data.jpa.repository.PersonPagingAndSortingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

//@Configuration
public class PagingAndSortingRepositoryConfig {

    private static final Logger logger = LoggerFactory.getLogger(PagingAndSortingRepositoryConfig.class);

    @Autowired
    public PersonPagingAndSortingRepository repository;

    @Bean
    public CommandLineRunner pagingAndSortingData() {
        return args -> {
            loadData();
            paginate();
            sort();
            paginateAndSort();
        };
    }

    private void loadData() {
        // add 5 persons
        repository.save(new Person("paul", 32));
        repository.save(new Person("alina", 28));
        repository.save(new Person("kazimir", 34));
        repository.save(new Person("lidia", 32));
        repository.save(new Person("violeta", 32));
    }

    private void paginate() {
        // pageable - interface with pagination info
        // page request - implementation
        // first page with two elements
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        Pageable secondPageWithOneElement = PageRequest.of(1, 1);

        Page<Person> page = repository.findAll(firstPageWithTwoElements);
        printPageContent(page);

        // people age 32 from first page
        Page<Person> peopleAge32Page = repository.findAllByAge(32, firstPageWithTwoElements);
        printPageContent(peopleAge32Page);
    }

    // print page content
    private void printPageContent(Page<Person> page) {
        page.getContent()
            .forEach(person -> logger.info("person: {}", person));
    }

    private void sort() {
        Sort sortByName = Sort.by("name");
        Iterable<Person> sortedPeople = repository.findAll(sortByName);
        sortedPeople.forEach(person -> logger.info("person: {}", person));
    }

    private void paginateAndSort() {
        // sort by name - first page, 5 / pag
        Pageable sortedByName = PageRequest.of(0, 5, Sort.by("name"));
        // sort by age descending - first page, 4 / pag
        Pageable sortedByAgeDescending = PageRequest.of(0, 4, Sort.by("age").descending());
        // sort by age descending and name ascending - first page, 5 / pag
        Pageable sortedByAgeDescendingAndNameAscending = PageRequest.of(0, 5,
            Sort.by("age").descending()
                .and(Sort.by("name").ascending()));

        Page<Person> peopleSortedByName = repository.findAll(sortedByName);
        Page<Person> peopleSortedByAgeDescending = repository.findAll(sortedByAgeDescending);
        Page<Person> peopleSortedByAgeDescendingAndNameAscending = repository.findAll(sortedByAgeDescendingAndNameAscending);

        printPageContent(peopleSortedByName);
        printPageContent(peopleSortedByAgeDescending);
        printPageContent(peopleSortedByAgeDescendingAndNameAscending);
    }
}
