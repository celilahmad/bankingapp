package app.controller;

import app.entity.Category;
import app.entity.Comment;
import app.entity.Post;
import app.service.CategoryService;
import app.service.CommentService;
import app.service.EmailServiceImpl;
import app.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PostDetailController {

    private final CategoryService categoryService;
    private final PostService postService;
    private final CommentService commentService;
    private final EmailServiceImpl emailService;

    public PostDetailController(CategoryService categoryService, PostService postService, CommentService commentService, EmailServiceImpl emailService) {
        this.categoryService = categoryService;
        this.postService = postService;
        this.commentService = commentService;
        this.emailService = emailService;
    }

    @GetMapping("/detail/{id}")
    public String postDetail(Model model,
                             @PathVariable("id") int id){
        List<Category> categories = categoryService.allCategory();
        List<Post> relatedPosts = postService.relatedPosts(id);
        Post post = postService.getPost(id);
        List<Post> latestPosts = postService.latestPosts();
        List<Comment> comments = commentService.postComments(id);
        List<Post> mostComments = commentService.mostCommented();

        model.addAttribute("mostComments", mostComments);
        model.addAttribute("comments", comments);
        model.addAttribute("latestPosts", latestPosts);
        model.addAttribute("relatedPosts", relatedPosts);
        model.addAttribute("categories", categories);
        model.addAttribute("post", post);
        return "single";
    }

    @PostMapping("/detail/{id}")
    public String postComment(@PathVariable("id") int id,
                              @RequestParam("fullName") String fullName,
                              @RequestParam("email") String email,
                              @RequestParam("comment") String comment){

        emailService.send(email, "Sport news", ("You have successfully commented\n\n" + comment));
        String date = LocalDate.now().toString();
        commentService.saveComment(new Comment(fullName, email, comment, date, new Post(id)));
        return "redirect:/detail/" + id;
    }
}
