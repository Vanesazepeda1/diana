package org.esfe.services.implement;

import org.esfe.models.subjects;
import org.esfe.repository.isubjectsrepository;
import org.esfe.services.interfaces.isubjectsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class subjectsservice implements isubjectsservice {
    @Autowired
    private isubjectsrepository subjectsrepository ;

    @Override
    public Page<subjects> findAll(Pageable pageable) {
        return subjectsrepository.findAll(pageable);
    }

    @Override
    public List<subjects> getAll() {
        return subjectsrepository.findAll();
    }

    @Override
    public Optional<subjects> findOneById(Integer subjectsid) {
        return subjectsrepository.findById(subjectsid);
    }

    @Override
    public subjects createOrEditOne(subjects subjects) {
        return subjectsrepository.save(subjects);
    }

    @Override
    public void deleteOneById(Integer subjectsid) {
        subjectsrepository.deleteById(subjectsid);
    }
}