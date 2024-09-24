package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Searchhistory {
    private String user_id;
    private String search;

    @Schema(description = "MM-DD", example = "08-12")
    private Timestamp search_date;

}