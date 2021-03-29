package edu.ada.service.library.controller;

import edu.ada.service.library.model.requestAndResponse.SearchBookParams;
import edu.ada.service.library.model.requestAndResponse.SearchAndOr;
import edu.ada.service.library.model.requestAndResponse.BookDto;
import edu.ada.service.library.model.requestAndResponse.CategoryDto;
import edu.ada.service.library.service.LibraryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/library/public")
public class LibraryPublicController {
    private LibraryService libraryService;

    public LibraryPublicController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping(value="/categories")
    public List<CategoryDto> listCategories() {
        return libraryService.listCategories();
    }

    @GetMapping(value="/books")
    public List<BookDto> listBooks() {
        return libraryService.listBooks();
    }

    @GetMapping(value="/searchBooks")
    public List<BookDto> searBooks(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "searchAndOr", required = false) SearchAndOr searchAndOr
    ) {
        return libraryService.searchBooks(
                SearchBookParams.builder().name(name).categoryId(categoryId).author(author).searchAndOr(searchAndOr).build()
        );
    }
}
