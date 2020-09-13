package com.sda.spring.data.jpa.repository;

import com.sda.spring.data.jpa.model.Person;
import org.springframework.data.repository.Repository;

// Repository<ObjectType, IdType>
public interface PersonRepository extends Repository<Person, Long> {
}
