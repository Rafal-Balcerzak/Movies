package pl.balcerzak.nowekolory.exception;

public class MovieException extends RuntimeException {
    private MovieError movieError;

    public MovieException(MovieError movieError){
        this.movieError = movieError;
    }

    public MovieError getMovieError(){
        return movieError;
    }

}
