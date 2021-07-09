package pl.balcerzak.nowekolory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import pl.balcerzak.nowekolory.model.Movie;
import pl.balcerzak.nowekolory.model.MovieDto;
import pl.balcerzak.nowekolory.model.UserDto;
import pl.balcerzak.nowekolory.service.MovieServices;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionScope
public class MovieController {

    private final MovieServices movieServices;
    private List<MovieDto> movies;
    private UserDto userMovies;
    private String userName;

    public MovieController(MovieServices movieServices) {
        this.movieServices = movieServices;
        movies = new ArrayList<>();
        userMovies = new UserDto();
    }


    @GetMapping("/movies")
    public String get(Model model) {
        userName = movieServices.getUserName();

        model.addAttribute("movies", movies);
        model.addAttribute("user", userName);
        model.addAttribute("userMovies", userMovies);
        return "index";
    }

    @PostMapping(value = "/search-movie", params = "search")
    public String getMovieDto(@RequestParam(value = "title") String title,
                              @RequestParam(value = "year") int year) {
        movies.clear();
        movies.add(movieServices.getMovieByTitleAndYear(title, year));
        return "redirect:/movies";
    }

    @PostMapping("/movie/{title}/{year}")
    public Movie addMovie(@PathVariable String title, @PathVariable int year) {
        return movieServices.addMovie(movieServices.getMovieByTitleAndYear(title, year));
    }

    @PostMapping(value = "/search-movie", params = "add")
    public String addFavoriteMovieToUser(@RequestParam(value = "title") String title,
                                         @RequestParam(value = "year") int year) {
        userMovies = movieServices.addFavoriteMovieToUser(userName, title, year);
        return "redirect:/movies";
    }

}
