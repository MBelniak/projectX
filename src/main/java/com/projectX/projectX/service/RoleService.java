package com.projectX.projectX.service;

import com.projectX.projectX.domain.Role;
import com.projectX.projectX.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRole(String roleName)
    {
        return roleRepository.findRoleByName(roleName);
    }
}
