package bbc.news.elections.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiResponse {
    String error;
    String message;
}
