package com.work.veloclub.service.role;


import com.work.veloclub.model.role.RoleDtoDeprecated;
import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDtoDeprecated> getRoles() {
        return roleRepository.findAll().stream().map(role -> new RoleDtoDeprecated(role.getId(), role.getName(), role.getQualification())).collect(Collectors.toList());
    }

    @Override
    public List<RoleDtoDeprecated> getQualifications() {
        return roleRepository.findAllByNameNot(RoleEnum.ADMIN).stream().map(role -> new RoleDtoDeprecated(role.getId(), role.getName(), role.getQualification())).collect(Collectors.toList());
    }

    @Override
    public String getQualificationByUserId(Long userId) {
        return roleRepository.findRoleByUser(userId);
    }
}
