package main.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = IdDocumentValidation.class)
public @interface IdDocumentExists {
    String message() default "{IdDocumentExists}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}