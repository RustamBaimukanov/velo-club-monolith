package by.itminsk.cyclingclubbackend.service.role;

import by.itminsk.cyclingclubbackend.model.role.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> getRoles();


    List<RoleDto> getQualifications();

    String getQualificationByUserId(Long userId);
}
