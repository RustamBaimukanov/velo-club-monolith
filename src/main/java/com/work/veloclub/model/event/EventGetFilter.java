package com.work.veloclub.model.event;

import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.user.GenderEnum;

import java.util.List;

public record EventGetFilter(RoleEnum role, Integer month, Integer year, Long cityId, GenderEnum gender, Integer birthDateFrom, Integer birthDateTo, List<Integer> categories) {
}
