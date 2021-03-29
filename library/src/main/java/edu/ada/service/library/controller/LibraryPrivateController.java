package edu.ada.service.library.controller;

import edu.ada.service.library.model.entity.UserEntity;
import edu.ada.service.library.model.requestAndResponse.PickupRequestDto;
import edu.ada.service.library.model.requestAndResponse.PickupDto;
import edu.ada.service.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/library/private")
public class LibraryPrivateController {
    @Autowired
    private LibraryService libraryService;


    @GetMapping(value="/currentPickups")
    public List<PickupDto> getCurrentPickups(@RequestAttribute(name = "user") UserEntity userEntity) {
        return libraryService.listCurrentPickups(userEntity);
    }

    @GetMapping(value="/history")
    public List<PickupDto> getPickupDropOffHistory(@RequestAttribute(name = "user") UserEntity userEntity) {
         return libraryService.listPickupAndDropOffs(userEntity);
    }

    @PostMapping(value="/pickup")
    public PickupDto pickupBook(
            @Valid @RequestBody PickupRequestDto requestDto,
            @RequestAttribute(name = "user") UserEntity userEntity
    ) {
            return libraryService.pickupBook(requestDto, userEntity);
    }

    @PostMapping(value="/dropoff")
    public PickupDto dropOffBook(
            @Valid @RequestBody PickupRequestDto requestDto,
            @RequestAttribute(name = "user") UserEntity userEntity
    ) {
        return libraryService.dropOffBook(requestDto, userEntity);
    }
}
