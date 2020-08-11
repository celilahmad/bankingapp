package app.controller;

import app.entity.Category;
import app.entity.Post;
import app.service.CategoryService;
import app.service.CommentService;
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
    private final CommentService commentService;

    public SearchController(CategoryService categoryService, PostService postService, CommentService commentService) {
        this.categoryService = categoryService;
        this.postService = postService;
        this.commentService = commentService;
    }


    @GetMapping
    public String postSearch(@RequestParam("title") String title, Model model){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.format(formatter);
        List<Category> categories = categoryService.allCategory();
        List<Post> searchPost = postService.searchResult(title);
        List<Post> latestPosts = postService.latestPosts();
        List<Post> mostComments = commentService.mostCommented();

        model.addAttribute("mostComments", mostComments);
        model.addAttribute("latestPosts", latestPosts);
        model.addAttribute("date", date);
        model.addAttribute("categories", categories);
        model.addAttribute("searchResult", searchPost);
        return "search-result";

    }
}
