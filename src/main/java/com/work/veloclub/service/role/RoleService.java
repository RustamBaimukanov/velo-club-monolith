package com.work.veloclub.service.role;

import com.work.veloclub.model.role.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> getRoles();


    List<RoleDto> getQualifications();

    String getQualificationByUserId(Long userId);
}
