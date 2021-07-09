package pl.balcerzak.nowekolory.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.balcerzak.nowekolory.model.UserDto;
import pl.balcerzak.nowekolory.security.AppUser;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "favoriteMovies", target = "favoriteMovies")
    UserDto appUserToUserDto(AppUser appUser);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "favoriteMovies", target = "favoriteMovies")
    AppUser userDtoToAppUser(UserDto userDto);
}
