package com.work.veloclub.service.role;

import com.work.veloclub.model.role.RoleDtoDeprecated;

import java.util.List;

public interface RoleService {

    List<RoleDtoDeprecated> getRoles();


    List<RoleDtoDeprecated> getQualifications();

    String getQualificationByUserId(Long userId);
}
