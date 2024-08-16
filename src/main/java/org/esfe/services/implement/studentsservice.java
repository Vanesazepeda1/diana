package org.esfe.services.implement;

import org.esfe.models.notes;
import org.esfe.models.students;
import org.esfe.repository.istudentsrepository;
import org.esfe.services.interfaces.istudentsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class studentsservice implements istudentsservice {
    @Autowired
    private istudentsrepository studentsrepository ;

    @Override
    public Page<students> findAll(Pageable pageable) {
        return  studentsrepository.findAll(pageable);
    }

    @Override
    public List<students> getAll() {
        return studentsrepository.findAll();
    }

    @Override
    public Optional<students> findOneById(Integer studentsid) {
        return studentsrepository.findById(studentsid);
    }


    @Override
    public students createOrEditOne(students students) {
        return studentsrepository.save(students);
    }


    @Override
    public void deleteOneById(Integer studentsid) {
        studentsrepository.deleteById(studentsid);
    }
}

