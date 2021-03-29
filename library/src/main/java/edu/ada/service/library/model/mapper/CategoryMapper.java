package edu.ada.service.library.model.mapper;

import edu.ada.service.library.model.entity.CategoryEntity;
import edu.ada.service.library.model.requestAndResponse.CategoryDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CategoryMapper {
    public static CategoryDto mapEntityToDto(CategoryEntity categoryEntity) {
        return CategoryDto.builder().id(categoryEntity.getId()).name(categoryEntity.getName()).build();
    }

    public static List<CategoryDto> mapEntitiesToDtos(Iterable<CategoryEntity> categories) {
        return StreamSupport.stream(categories.spliterator(), false).map(CategoryMapper::mapEntityToDto).collect(Collectors.toList());
    }
}
