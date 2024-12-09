package com.work.veloclub.util.exception_handler.custom_validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatches {
    String message() default "Пароль и его подтверждение не совпадают.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
