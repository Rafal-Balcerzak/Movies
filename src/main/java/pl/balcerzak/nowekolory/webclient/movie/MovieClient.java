package pl.balcerzak.nowekolory.webclient.movie;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.balcerzak.nowekolory.model.MovieDto;
import pl.balcerzak.nowekolory.webclient.movie.dto.OpenMovieDto;

@Component
public class MovieClient {

    public static final String MOVIE_URL = "http://www.omdbapi.com/?";
    public static final String API_KEY = "94903105";
    private RestTemplate restTemplate = new RestTemplate();

    public MovieDto getMovieByTitleAndYear(String title, int year){
        OpenMovieDto openMovieDto = restTemplate.getForObject(MOVIE_URL + "apikey={apiKey}&t={title}}&y={year}",
                OpenMovieDto.class, API_KEY, title, year);

        return MovieDto.builder()
                .title(openMovieDto.getTitle())
                .plot(openMovieDto.getPlot())
                .genre(openMovieDto.getGenre())
                .director(openMovieDto.getDirector())
                .poster(openMovieDto.getPoster())
                .build();
    }
}
