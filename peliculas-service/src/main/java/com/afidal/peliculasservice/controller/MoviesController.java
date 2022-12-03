package com.afidal.peliculasservice.controller;

import com.afidal.peliculasservice.model.Movie;
import com.afidal.peliculasservice.model.dto.AddCommentRequest;
import com.afidal.peliculasservice.model.dto.CreateMovieRequest;
import com.afidal.peliculasservice.model.dto.MovieDto;
import com.afidal.peliculasservice.model.dto.UpdateMovieRequest;
import com.afidal.peliculasservice.mapper.MovieMapper;
import com.afidal.peliculasservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MoviesController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @PreAuthorize("(hasAuthority('GROUP_ADMIN') AND hasRole('ROLE_ADMIN')) OR (hasAuthority('GROUP_CLIENT') AND hasRole('ROLE_CLIENT'))")
    @GetMapping
    public List<MovieDto> getMovies() {
        return movieService.getMovies().stream()
                .map(movieMapper::toMovieDto)
                .collect(Collectors.toList());
    }

    @PreAuthorize("(hasAuthority('GROUP_ADMIN') AND hasRole('ROLE_ADMIN')) OR (hasAuthority('GROUP_CLIENT') AND hasRole('ROLE_CLIENT'))")
    @GetMapping("/{imdbId}")
    public MovieDto getMovie(@PathVariable String imdbId) {
        Movie movie = movieService.validateAndGetMovie(imdbId);
        return movieMapper.toMovieDto(movie);
    }

    @PreAuthorize("hasAuthority('GROUP_ADMIN') AND hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MovieDto createMovie(@Valid @RequestBody CreateMovieRequest createMovieRequest) {
        Movie movie = movieMapper.toMovie(createMovieRequest);
        movie = movieService.saveMovie(movie);
        return movieMapper.toMovieDto(movie);
    }

    @PreAuthorize("hasAuthority('GROUP_ADMIN') AND hasRole('ROLE_ADMIN')")
    @PutMapping("/{imdbId}")
    public MovieDto updateMovie(@PathVariable String imdbId, @Valid @RequestBody UpdateMovieRequest updateMovieRequest) {
        Movie movie = movieService.validateAndGetMovie(imdbId);
        movieMapper.updateMovieFromDto(updateMovieRequest, movie);
        movie = movieService.saveMovie(movie);
        return movieMapper.toMovieDto(movie);
    }

    @PreAuthorize("hasAuthority('GROUP_ADMIN') AND hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{imdbId}")
    public MovieDto deleteMovie(@PathVariable String imdbId) {
        Movie movie = movieService.validateAndGetMovie(imdbId);
        movieService.deleteMovie(movie);
        return movieMapper.toMovieDto(movie);
    }

    @PreAuthorize("(hasAuthority('GROUP_ADMIN') AND hasRole('ROLE_ADMIN')) OR (hasAuthority('GROUP_CLIENT') AND hasRole('ROLE_CLIENT'))")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{imdbId}/comments")
    public MovieDto addMovieComment(@PathVariable String imdbId, @Valid @RequestBody AddCommentRequest addCommentRequest, Principal principal) {
        Movie movie = movieService.validateAndGetMovie(imdbId);
        Movie.Comment comment = new Movie.Comment(principal.getName(), addCommentRequest.getText(), LocalDateTime.now());
        movie.getComments().add(0, comment);
        movie = movieService.saveMovie(movie);
        return movieMapper.toMovieDto(movie);
    }
}