package edu.ada.service.library.model.mapper;

import edu.ada.service.library.model.entity.BookEntity;
import edu.ada.service.library.model.entity.PickupEntity;
import edu.ada.service.library.model.requestAndResponse.BookDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookMapper {
    public static BookDto mapEntityToDto(BookEntity bookEntity) {
        PickupEntity pickupEntity = bookEntity.getCurrentPickup();


        return BookDto.builder().id(bookEntity.getId()).name(bookEntity.getName()).author(bookEntity.getAuthor()).publishDate(bookEntity.getPublishDate()).category(CategoryMapper.mapEntityToDto(bookEntity.getCategoryEntity())).isAvailable(pickupEntity == null).pickerEmail(pickupEntity != null ? pickupEntity.getUserEntity().getEmail() : null).build();
    }

    public static List<BookDto> mapEntitiesToDtos(Iterable<BookEntity> categories) {
        return StreamSupport.stream(categories.spliterator(), false).map(BookMapper::mapEntityToDto).collect(Collectors.toList());
    }
}
