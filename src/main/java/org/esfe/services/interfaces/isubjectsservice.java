package org.esfe.services.interfaces;

import org.esfe.models.subjects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface isubjectsservice {

    Page<subjects> findAll(Pageable pageable);

    List<subjects> getAll();

    Optional<subjects> findOneById(Integer subjectsId);

    subjects createOrEditOne(subjects subjects);

    void deleteOneById(Integer subjectsId);
}

