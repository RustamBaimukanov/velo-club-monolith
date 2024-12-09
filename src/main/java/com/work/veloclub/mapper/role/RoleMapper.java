package com.work.veloclub.mapper.role;

import com.work.veloclub.model.city.City;
import com.work.veloclub.model.city.CityDTO;
import com.work.veloclub.model.role.Role;
import com.work.veloclub.model.role.RoleDTO;

public class RoleMapper {

    public static RoleDTO mapToRoleDto(Role role){
        if (role == null){
            return null;
        }

        return new RoleDTO(role.getId(), role.getName(), role.getQualification());
    }
}
