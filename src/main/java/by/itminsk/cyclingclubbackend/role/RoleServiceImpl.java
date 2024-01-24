package by.itminsk.cyclingclubbackend.role;


import by.itminsk.cyclingclubbackend.role.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<RoleDto> getQualifications() {
        return roleRepository.findAllByIdIsNot(1L);
    }

    @Override
    public String getQualificationByUserId(Long userId) {
        return roleRepository.findRoleByUser(userId);
    }
}
