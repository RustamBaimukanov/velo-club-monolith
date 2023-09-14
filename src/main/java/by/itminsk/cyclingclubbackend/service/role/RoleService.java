package by.itminsk.cyclingclubbackend.service.role;

import by.itminsk.cyclingclubbackend.model.user.Role;
import by.itminsk.cyclingclubbackend.model.user.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> getQualifications();
}
