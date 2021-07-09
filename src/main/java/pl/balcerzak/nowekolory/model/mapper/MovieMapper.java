package pl.balcerzak.nowekolory.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.balcerzak.nowekolory.model.Movie;
import pl.balcerzak.nowekolory.model.MovieDto;

@Mapper
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "plot", target = "plot")
    @Mapping(source = "genre", target = "genre")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "poster", target = "poster")
    Movie movieDtoToMovie(MovieDto movieDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "plot", target = "plot")
    @Mapping(source = "genre", target = "genre")
    @Mapping(source = "director", target = "director")
    @Mapping(source = "poster", target = "poster")
    MovieDto movieToMovieDto(Movie movie);

}
