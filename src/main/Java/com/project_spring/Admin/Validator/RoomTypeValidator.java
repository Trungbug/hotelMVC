package com.project_spring.Admin.Validator;

import com.project_spring.Admin.Model.RoomType;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RoomTypeValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RoomType.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RoomType roomType = (RoomType) target;
        if(roomType.getTypeName() == null || roomType.getTypeName().length() == 0) {
            errors.rejectValue("typeName", "NotEmpty");
        }
        else if(roomType.getTypeName().length() > 100) {
            errors.rejectValue("typeName", "Length");
        }

        ValidationUtils.rejectIfEmpty(errors, "description", "NotEmpty");
        if(roomType.getDescription().length() > 100) {
            errors.rejectValue("description", "Length");
        }
    }
}
