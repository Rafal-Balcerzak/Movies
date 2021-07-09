package pl.balcerzak.nowekolory.exception;

public enum MovieError {
    MOVIE_NOT_FOUND("The movie does not exist");

    private String message;
    MovieError(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
