package com.example.newsmanagement.Controller;
import com.example.newsmanagement.ApiResopnse.ApiResponse;
import com.example.newsmanagement.Model.News;
import com.example.newsmanagement.Service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/get-news")
    public ResponseEntity getNews() {
        if (newsService.getNews() == null)
            return ResponseEntity.status(400).body(new ApiResponse("We don't have any News Articles yet"));

        return ResponseEntity.status(200).body(newsService.getNews());
    }

    @PostMapping("/add-news")
    public ResponseEntity addNews(@RequestBody @Valid News news1, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        newsService.addNews(news1);
        return ResponseEntity.status(200).body(new ApiResponse("News Article has been added successfully"));
    }

    @PutMapping("/update-news/{id}")
    public ResponseEntity updateNews(@PathVariable String id, @RequestBody @Valid News news1, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (newsService.updateNews(id, news1))
            return ResponseEntity.status(200).body(new ApiResponse("News Article with ID: " + id + " has been updated successfully"));

        return ResponseEntity.status(404).body(new ApiResponse("News Article with ID: " + id + " was not found"));
    }

    @DeleteMapping("/delete-news/{id}")
    public ResponseEntity deleteNews(@PathVariable String id) {
        if (newsService.deleteNews(id))
            return ResponseEntity.status(200).body(new ApiResponse("News Article with ID: " + id + " has been deleted successfully"));

        return ResponseEntity.status(404).body(new ApiResponse("News Article with ID: " + id + " was not found"));
    }

    @PutMapping("/publish-news/{id}")
    public ResponseEntity publishNews(@PathVariable String id) {
        if (newsService.publishNews(id))
            return ResponseEntity.status(200).body(new ApiResponse("News Article with ID: " + id + " has been published successfully"));

        return ResponseEntity.status(404).body(new ApiResponse("News Article with ID: " + id + " was not found"));
    }

    @GetMapping("/get-published-news")
    public ResponseEntity getPublishedNews() {
        if (newsService.getPublishedNews() == null)
            return ResponseEntity.status(404).body(new ApiResponse("There is no published News Articles yet"));

        return ResponseEntity.status(200).body(newsService.getPublishedNews());
    }

    @GetMapping("/get-by-category/{category}")
    public ResponseEntity getNewsByCategory(@PathVariable String category) {
        if (newsService.getByCategory(category) == null)
            return ResponseEntity.status(404).body(new ApiResponse("There is no News Articles in this category"));

        return ResponseEntity.status(200).body(newsService.getByCategory(category));
    }
}
