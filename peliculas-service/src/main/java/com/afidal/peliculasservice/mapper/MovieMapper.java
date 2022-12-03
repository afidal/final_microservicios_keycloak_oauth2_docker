package com.afidal.peliculasservice.mapper;

import com.afidal.peliculasservice.model.Movie;
import com.afidal.peliculasservice.model.dto.CreateMovieRequest;
import com.afidal.peliculasservice.model.dto.MovieDto;
import com.afidal.peliculasservice.model.dto.UpdateMovieRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class MovieMapper {


    @Mapping(target = "comments", ignore = true)
    public abstract Movie toMovie(CreateMovieRequest createMovieRequest);

    @Mapping(target = "imdbId", ignore = true)
    @Mapping(target = "comments", ignore = true)
    public abstract void updateMovieFromDto(UpdateMovieRequest updateMovieRequest, @MappingTarget Movie movie);

    public abstract MovieDto toMovieDto(Movie movie);

   //TODO traer el avatar correspondiente al username
    public abstract MovieDto.CommentDto toMovieDtoCommentDto(Movie.Comment comment);
}