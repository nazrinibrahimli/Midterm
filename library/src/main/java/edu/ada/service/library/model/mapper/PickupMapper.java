package edu.ada.service.library.model.mapper;

import edu.ada.service.library.model.entity.PickupEntity;
import edu.ada.service.library.model.requestAndResponse.PickupDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PickupMapper {
    public static PickupDto mapEntityToDto(PickupEntity pickupEntity) {
        return PickupDto.builder().id(pickupEntity.getId()).dropOff(pickupEntity.isDropOff()).book(BookMapper.mapEntityToDto(pickupEntity.getBookEntity())).createdAt(pickupEntity.getCreatedAt()).updatedAt(pickupEntity.getUpdatedAt()).build();
    }

    public static List<PickupDto> mapEntitiesToDtos(Iterable<PickupEntity> pickups) {
        return StreamSupport.stream(pickups.spliterator(), false).map(PickupMapper::mapEntityToDto).collect(Collectors.toList());
    }
}
