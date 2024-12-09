package com.work.veloclub.mapper.trophy;

import com.work.veloclub.model.role.Role;
import com.work.veloclub.model.role.RoleDTO;
import com.work.veloclub.model.trophy.Trophy;
import com.work.veloclub.model.trophy.TrophyDTO;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TrophyMapper {

    public static TrophyDTO mapToTrophyDto(Trophy trophy){
        if (trophy == null){
            return null;
        }

        return new TrophyDTO(trophy.getId(), trophy.getDescription(), trophy.getTrophyType().getId(), trophy.getTrophyGroup().getId());
    }

    public static List<TrophyDTO> mapToTrophyDtoList(Set<Trophy> trophies) {
        if (trophies == null || trophies.isEmpty()) {
            return Collections.emptyList();  // Возвращаем пустой список, если исходный список null или пустой
        }

        return trophies.stream()
                .map(TrophyMapper::mapToTrophyDto)  // Применяем преобразование к каждому элементу
                .collect(Collectors.toList());      // Собираем результаты в список
    }
}
