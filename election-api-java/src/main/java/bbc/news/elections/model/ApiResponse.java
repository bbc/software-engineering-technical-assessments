package bbc.news.elections.model;

public class ApiResponse {
    private final String error;
    private final String message;

    public ApiResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
