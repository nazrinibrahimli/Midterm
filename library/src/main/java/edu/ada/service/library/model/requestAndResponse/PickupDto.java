package edu.ada.service.library.model.requestAndResponse;

import edu.ada.service.library.model.requestAndResponse.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PickupDto {
    private Long id;
    private BookDto book;
    private boolean dropOff;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
