package by.itminsk.cyclingclubbackend.role;


import by.itminsk.cyclingclubbackend.role.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<RoleDto> getQualifications() {
        return roleRepository.findAll().stream().map(role -> new RoleDto(role.getId(), role.getName(), role.getQualification())).collect(Collectors.toList());
    }

    @Override
    public String getQualificationByUserId(Long userId) {
        return roleRepository.findRoleByUser(userId);
    }
}
