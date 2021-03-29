package edu.ada.service.library.model.requestAndResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Long id;
    private String name;
    private String author;
    private CategoryDto category;
    private boolean isAvailable;
    private LocalDate publishDate;
    private String pickerEmail;

}
