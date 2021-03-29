package edu.ada.service.library.service.impl;

import edu.ada.service.library.controller.ErrorHandler;
import edu.ada.service.library.exceptions.InvalidPickupException;
import edu.ada.service.library.exceptions.NotExistsException;
import edu.ada.service.library.model.entity.BookEntity;
import edu.ada.service.library.model.entity.CategoryEntity;
import edu.ada.service.library.model.entity.PickupEntity;
import edu.ada.service.library.model.entity.UserEntity;
import edu.ada.service.library.model.mapper.BookMapper;
import edu.ada.service.library.model.mapper.CategoryMapper;
import edu.ada.service.library.model.mapper.PickupMapper;
import edu.ada.service.library.model.repository.BookRepository;
import edu.ada.service.library.model.repository.CategoryRepository;
import edu.ada.service.library.model.repository.PickupRepository;
import edu.ada.service.library.model.requestAndResponse.PickupRequestDto;
import edu.ada.service.library.model.requestAndResponse.SearchBookParams;
import edu.ada.service.library.model.requestAndResponse.SearchAndOr;
import edu.ada.service.library.model.requestAndResponse.BookDto;
import edu.ada.service.library.model.requestAndResponse.CategoryDto;
import edu.ada.service.library.model.requestAndResponse.PickupDto;
import edu.ada.service.library.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service



public class LibraryServiceImpl implements LibraryService {
    protected static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);




    private BookRepository bookRepository;

    private CategoryRepository categoryRepository;
    private PickupRepository pickupRepository;

    public LibraryServiceImpl(
            BookRepository bookRepository, CategoryRepository categoryRepository, PickupRepository pickupRepository
    ) {
          this.bookRepository = bookRepository;
           this.categoryRepository = categoryRepository;
        this.pickupRepository = pickupRepository;
    }

    @Override
    public List<CategoryDto> listCategories() {
        log.info(" ");
        Iterable<CategoryEntity> categories = categoryRepository.findAll();
        log.info(" ");
        return CategoryMapper.mapEntitiesToDtos(categories);
    }

    @Override
    public List<BookDto> listBooks() {
        log.info(" ");
        var books = bookRepository.findAll();
        log.info(" ");
        return BookMapper.mapEntitiesToDtos(books);
    }

    @Override
    public List<BookDto> searchBooks(SearchBookParams params) {
        log.info(" ");

        List<BookEntity> bookEntities;
        if (params.getSearchAndOr() == SearchAndOr.AND) {
            bookEntities = bookRepository.findAllByNameContainingAndCategory_idAndAuthorContaining(
                    params.getName(),
                    params.getCategoryId(),
                    params.getAuthor()
            );
        } else {
            bookEntities = bookRepository.findAllByNameContainingOrCategory_idOrAuthorContaining(
                    params.getName(),
                    params.getCategoryId(),
                    params.getAuthor()
            );
        }

        log.info(" ");
        return BookMapper.mapEntitiesToDtos(bookEntities);
    }

    @Override
    public List<PickupDto> listCurrentPickups(UserEntity userEntity) {
        log.info(" ");

        var pickups = pickupRepository.findByUserAndDropOffFalse(userEntity);

        log.info(" ");
        return PickupMapper.mapEntitiesToDtos(pickups);
    }

    @Override
    public List<PickupDto> listPickupAndDropOffs(UserEntity userEntity) {
        log.info(" ");

        var pickups = pickupRepository.findByUser(userEntity);

        log.info(" ");
        return PickupMapper.mapEntitiesToDtos(pickups);
    }

    @Override
    public PickupDto pickupBook(PickupRequestDto requestDto, UserEntity userEntity) {
        log.info(" ");

        BookEntity bookEntity = bookRepository
                .findById(requestDto.getBookId())
                .orElseThrow(() -> new NotExistsException(" "));

        PickupEntity pickupEntity = pickupRepository.findByBookAndDropOffFalse(bookEntity);

        if (pickupEntity != null) {
            if (pickupEntity.getUserEntity().getId().equals(userEntity.getId())) {
                throw new InvalidPickupException(" ");
            }
            throw new InvalidPickupException(" ");
        }

        var newPickup = PickupEntity
                .builder().bookEntity(bookEntity).userEntity(userEntity).build();

        pickupRepository.save(newPickup);

        log.info(" ");
        return PickupMapper.mapEntityToDto(newPickup);
    }

    @Override
    public PickupDto dropOffBook(PickupRequestDto requestDto, UserEntity userEntity) {
        log.info(" ");

        BookEntity bookEntity = bookRepository
                .findById(requestDto.getBookId()).orElseThrow(() -> new NotExistsException("Book cannot be found"));

        PickupEntity pickupEntity = pickupRepository.findByBookAndUserAndDropOffFalse(bookEntity, userEntity);

        if (pickupEntity == null) {
            throw new InvalidPickupException("error");
        }
        pickupEntity.setDropOff(true);
        pickupRepository.save(pickupEntity);
        log.info(" ");
        return PickupMapper.mapEntityToDto(pickupEntity);
    }
}
