package org.esfe.services.interfaces;

import org.esfe.models.notes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface inotesservice {

    Page<notes> findAll(Pageable pageable);

    List<notes> getAll();

    Optional<notes> findOneById(Integer notesId);

    notes createOrEditOne(notes notes);

    void deleteOneById(Integer notesId);
}
