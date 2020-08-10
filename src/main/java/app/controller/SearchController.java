package app.controller;

import app.entity.Category;
import app.entity.Post;
import app.service.CategoryService;
import app.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final CategoryService categoryService;
    private final PostService postService;

    public SearchController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }


    @PostMapping
    public String postSearch(@RequestParam("title") String title, Model model){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.format(formatter);
        List<Category> categories = categoryService.allCategory();
        List<Post> searchPost = postService.searchResult(title);
        model.addAttribute("date", date);
        model.addAttribute("categories", categories);
        model.addAttribute("searchResult", searchPost);
        return "search-result";

    }
}
