package app.controller;

import app.entity.Category;
import app.entity.Post;
import app.service.CategoryService;
import app.service.CommentService;
import app.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
public class IndexController {

    private final CategoryService categoryService;
    private final PostService postService;
    private final CommentService commentService;

    public IndexController(CategoryService categoryService, PostService postService, CommentService commentService) {
        this.categoryService = categoryService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @RequestMapping("/")
    public String home(Model model){

        return getHome(model, 1);
    }

    @GetMapping("/page/{currentPage}")
    public String getHome(Model model,
                          @PathVariable("currentPage") int currentPage) {
        Page<Post> page = postService.listAll(currentPage);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Integer> listTotalPages = IntStream.rangeClosed(1, page.getTotalPages()).boxed().collect(Collectors.toList());
        List<Post> all = page.getContent();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.format(formatter);
        List<Category> categories = categoryService.allCategory();
        List<Post> latestPosts = postService.latestPosts();
        List<Post> mostComments = commentService.mostCommented();

        model.addAttribute("mostComments", mostComments);
        model.addAttribute("latestPosts", latestPosts);
        model.addAttribute("all", all);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listTotalPages", listTotalPages);
        model.addAttribute("categories", categories);
        model.addAttribute("date", date);
        return "index";
    }

    @GetMapping("/category")
    public String getAbout(){
        return "category";
    }

    @GetMapping("/single")
    public String getContact(){
        return "single";
    }

    @GetMapping("/page")
    public String getPage(){
        return "category";
    }

}
