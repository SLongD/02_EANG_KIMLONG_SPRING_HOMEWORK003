package org.kps.exceptionhadling.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ApiResponse <T> {
    private String message;
    private T payload;
    private HttpStatus status;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
