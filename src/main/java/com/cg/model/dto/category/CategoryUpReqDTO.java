package com.cg.model.dto.category;

import com.cg.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CategoryUpReqDTO implements Validator {
    private String title;

    public Category toCategory(Long categoryId) {
        return new Category()
                .setId(categoryId)
                .setTitle(title)
                ;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryUpReqDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryUpReqDTO categoryUpReqDTO = (CategoryUpReqDTO) target;

        String title = categoryUpReqDTO.title;

        if (title.isEmpty()) {
            errors.rejectValue("title", "title.null", "Tên không được phép rỗng");
            return;
        }

        if (title.length() >= 40 || title.length() <= 2) {
            errors.rejectValue("title", "title.length", "Tên món không ít hơn 2 kí tự và dài hơn 40 kí tự");
        }
    }
}
