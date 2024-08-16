package org.esfe.services.interfaces;

import org.esfe.models.students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface istudentsservice {

    Page<students> findAll(Pageable pageable);

    List<students> getAll();

    Optional<students> findOneById(Integer studentsId);

    students createOrEditOne(students students);

    void deleteOneById(Integer studentsId);
}

