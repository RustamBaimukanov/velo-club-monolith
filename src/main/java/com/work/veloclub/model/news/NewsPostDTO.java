package com.work.veloclub.model.news;

import com.work.veloclub.model.role.RolesEnum;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NewsPostDTO {

    @NotBlank(message = "Название новости не может быть пустым")
    @Size(max = 255,message = "Название новости не может превышать 255 символов")
    private String title;

    @Size(max = 4000, message = "Описание новости не может превышать N символов")
    private String content;

    @NotNull(message = "Не выбрана категория получателей")
    private RolesEnum recipientCategory;

    private List<Long> addRecipient;





}
