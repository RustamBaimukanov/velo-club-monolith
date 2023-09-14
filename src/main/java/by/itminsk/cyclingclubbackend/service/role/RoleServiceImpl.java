package by.itminsk.cyclingclubbackend.service.role;


import by.itminsk.cyclingclubbackend.model.user.Role;
import by.itminsk.cyclingclubbackend.model.user.RoleDto;
import by.itminsk.cyclingclubbackend.repository.RoleRepository;
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
}
