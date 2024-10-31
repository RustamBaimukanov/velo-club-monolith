package com.work.veloclub.model.user;

import com.work.veloclub.model.role.RoleEnum;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record RegisterByAdminDto
        (String firstName,
         String lastName,
         String tel,
         String email,
         String password,
         Double height,
         Double weight,
         LocalDate birth,
         GenderEnum gender,
         Long region,
         Long club,
         MultipartFile userImg,
         RoleEnum qualification){
}
