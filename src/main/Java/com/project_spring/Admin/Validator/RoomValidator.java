package com.project_spring.Admin.Validator;

import com.project_spring.Admin.Model.Room;
import com.project_spring.Admin.Model.RoomType;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RoomValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Room.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Room room = (Room) target;

        try {

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

//        if(!ValidMoney.isValid(room.getPricePerNight())) {
//            errors.rejectValue("pricePerNight", "NotValid.money");
//        }
//        if(!ValidMoney.isValid(room.getArea())) {
//            errors.rejectValue("area", "NotValid.number");
//        }
//        if(!ValidMoney.isValid(room.getQuantity())) {
//            errors.rejectValue("quantity", "NotValid.number");
//        }
    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}
