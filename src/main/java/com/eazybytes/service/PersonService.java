package com.eazybytes.service;

import com.eazybytes.contants.EazySchoolConstants;
import com.eazybytes.model.Person;
import com.eazybytes.model.Roles;
import com.eazybytes.repository.PersonRepository;
import com.eazybytes.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PersonRepository personRepository;
    public boolean createNewPerson(Person person) {
        boolean isSaved = false;
        Roles roles = rolesRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE);
        person.setRoles(roles);
        person = personRepository.save(person);
        if (null != person && person.getPersonId()>0) {
            isSaved = true;
        }
        return isSaved;
    }
}
