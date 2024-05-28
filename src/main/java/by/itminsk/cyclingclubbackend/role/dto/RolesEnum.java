package by.itminsk.cyclingclubbackend.role.dto;

import java.util.HashSet;
import java.util.Set;

public enum RolesEnum {

    ANY,

    ADMIN,

    SPORTSMAN,

    DABBLER;


    public Set<RoleEnum> getRoleEnumSet(){
        switch (this){
            default -> {
                return Set.of(RoleEnum.SPORTSMAN, RoleEnum.DABBLER);
            }
            case ADMIN -> {
                return Set.of(RoleEnum.ADMIN);
            }
            case SPORTSMAN -> {
                return Set.of(RoleEnum.SPORTSMAN);
            }
            case DABBLER -> {
                return Set.of(RoleEnum.DABBLER);
            }
        }
    }


}
