package com.blog.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;

    @Size(min = 4, message = "Minimum size of category title is 4")
    private String categoryTitle;

    @Size(min = 10, message = "Minimum size of category title is 10")
    private String categoryDescription;
}
