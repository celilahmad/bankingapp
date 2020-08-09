package app.controller;

import app.entity.Category;
import app.entity.Post;
import app.service.CategoryService;
import app.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PostDetailController {

    private final CategoryService categoryService;
    private final PostService postService;

    public PostDetailController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @GetMapping("/detail/{id}")
    public String postDetail(Model model,
                             @PathVariable("id") int id){
        List<Category> categories = categoryService.allCategory();
        List<Post> relatedPosts = postService.relatedPosts(id);
        Post post = postService.getPost(id);
        model.addAttribute("relatedPosts", relatedPosts);
        model.addAttribute("categories", categories);
        model.addAttribute("post", post);
        return "single";
    }
}
