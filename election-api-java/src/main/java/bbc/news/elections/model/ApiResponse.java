package bbc.news.elections.model;

public class ApiResponse {
    final String error;
    final String message;

    public ApiResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
