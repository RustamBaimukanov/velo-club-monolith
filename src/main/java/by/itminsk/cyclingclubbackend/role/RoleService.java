package by.itminsk.cyclingclubbackend.role;

import by.itminsk.cyclingclubbackend.role.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> getRoles();


    List<RoleDto> getQualifications();

    String getQualificationByUserId(Long userId);
}
