package com.trendyol.services.book.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@JsonSerialize
@AllArgsConstructor
public class BookServiceErrorResponse {
    public String message;
}