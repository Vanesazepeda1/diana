package org.esfe.services.implement;

import org.esfe.models.notes;
import org.esfe.repository.inotesrepository;
import org.esfe.services.interfaces.inotesservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class notesservice implements inotesservice {
    @Autowired
    private inotesrepository notesrepository;

    @Override
    public Page<notes> findAll(Pageable pageable) {
        return notesrepository.findAll(pageable);
    }

    @Override
    public List<notes> getAll() {
        return notesrepository.findAll();
    }

    @Override
    public Optional<notes> findOneById(Integer notesid) {
        return notesrepository.findById(notesid);
    }

    @Override
    public notes createOrEditOne(notes notes) {
        return notesrepository.save(notes);
    }

    @Override
    public void deleteOneById(Integer notesid) {
        notesrepository.deleteById(notesid);
    }
}

