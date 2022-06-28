package com.trendyol.services.book.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookRequest {
    @NotBlank(message = "Field 'author' cannot be empty")
    String author;
    @NotBlank(message = "Field 'title' cannot be empty")
    String title;
}
