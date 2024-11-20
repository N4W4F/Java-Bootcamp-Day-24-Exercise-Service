package com.example.newsmanagement.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class News {

    @NotEmpty(message = "ID cannot be empty")
    @Size(max = 100)
    private String id;

    @NotEmpty(message = "Author cannot be empty")
    @Size(min = 5, max = 20, message = "Author must be between 5 and 20 characters")
    private String author;

    @NotEmpty(message = "Content cannot be empty")
    @Size(min = 201, message = "Content must be more than 200 characters")
    private String content;

    @NotEmpty(message = "Category cannot be empty")
    @Pattern(regexp = "^(Politics|Sports|Technology)$", message = "Category must be either Politics, Sports or Technology")
    private String category;

    @NotEmpty(message = "Image URL cannot be empty")
    private String imageUrl;

    @AssertFalse(message = "isPublished must be set initially false")
    private boolean isPublished;

    @NotNull(message = "Publish Date cannot be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
}
