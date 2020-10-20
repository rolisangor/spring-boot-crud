package crud.service;

import crud.model.Role;
import crud.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImp implements RoleService{

    private final RoleRepository roleRepo;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Role> findAll() {
        return roleRepo.findAll();
    }
}
