package pl.balcerzak.nowekolory.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.balcerzak.nowekolory.exception.MovieError;
import pl.balcerzak.nowekolory.exception.MovieException;
import pl.balcerzak.nowekolory.model.Movie;
import pl.balcerzak.nowekolory.model.MovieDto;
import pl.balcerzak.nowekolory.model.UserDto;
import pl.balcerzak.nowekolory.model.mapper.MovieMapper;
import pl.balcerzak.nowekolory.model.mapper.UserMapper;
import pl.balcerzak.nowekolory.repository.MovieRepository;
import pl.balcerzak.nowekolory.security.AppUser;
import pl.balcerzak.nowekolory.security.UserDetailsServiceImpl;
import pl.balcerzak.nowekolory.webclient.movie.MovieClient;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServices {

    private final MovieClient movieClient;
    private final MovieRepository movieRepository;
    private final UserDetailsServiceImpl userDetailsService;

    public MovieDto getMovieByTitleAndYear(String title, int year) {
        MovieDto movieDto = movieClient.getMovieByTitleAndYear(title, year);
        if (movieDto.getTitle() == null) {
            throw new MovieException(MovieError.MOVIE_NOT_FOUND);
        }
        return movieDto;
    }

    public Movie addMovie(MovieDto movieDto) {
        Movie movie = MovieMapper.INSTANCE.movieDtoToMovie(movieDto);
        if (!movieRepository.existsByTitle(movie.getTitle())) {
            movieRepository.save(movie);
        }
        Optional<Movie> optionalMovie = movieRepository.findByTitle(movie.getTitle());
        if (optionalMovie.isPresent()) {
            return optionalMovie.get();
        } else {
            throw new MovieException(MovieError.MOVIE_NOT_FOUND);
        }
    }

    public UserDto addFavoriteMovieToUser(String login, String title, int year) {
        MovieDto movieDto = getMovieByTitleAndYear(title, year);
        Movie movie = addMovie(movieDto);

        AppUser appUser = (AppUser) userDetailsService.loadUserByUsername(login);
        if (appUser.getFavoriteMovies().stream().noneMatch(fm -> movie.getTitle().equals(fm.getTitle()))) {
            appUser.getFavoriteMovies().add(movie);
            log.info("Film " + movie.getTitle() + " został dodany do ulubionych");
        } else {
            log.info("Film " + movie.getTitle() + " został wcześniej dodany do ulubionych");
        }

        userDetailsService.saveUser(appUser);
        return UserMapper.INSTANCE.appUserToUserDto(appUser);
    }
    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }
}










