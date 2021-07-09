package pl.balcerzak.nowekolory.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private Long id;
    private String title;
    private String plot;
    private String genre;
    private String director;
    private String poster;

}
