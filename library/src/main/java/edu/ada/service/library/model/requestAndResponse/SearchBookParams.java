package edu.ada.service.library.model.requestAndResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchBookParams {
    private String name;
    private String author;
    private Long categoryId;
    @Builder.Default
    private SearchAndOr searchAndOr = SearchAndOr.OR;
}
