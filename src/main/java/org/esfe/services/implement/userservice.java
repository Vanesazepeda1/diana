package org.esfe.services.implement;

import org.esfe.models.user;
import org.esfe.repository.iuserrepository;
import org.esfe.services.interfaces.iuserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class userservice  implements iuserservice {

    private iuserrepository userrepository;
    private PasswordEncoder passwordEncoder;

    public userservice(iuserrepository userrepository, PasswordEncoder passwordEncoder){
        this.userrepository = userrepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<user> findAll(Pageable pageable) {
        return userrepository.findAll(pageable);
    }

    @Override
    public List<user> getAll() {
        return userrepository.findAll();
    }

    @Override
    public Optional<user> findOneById(Integer userid) {
        return userrepository.findById(userid);
    }

    @Override
    public user createOrEditOne(user user) {
        user.setStatus("ACTIVO");
        user.setLogin("ACTIVO");
        user.setRegistrationDate(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userrepository.save(user);
    }

    @Override
    public void deleteOneById(Integer userid) {
        userrepository.deleteById(userid);
    }

    public user findByEmail(String email){
        return userrepository.findByEmail(email);
    }
}
