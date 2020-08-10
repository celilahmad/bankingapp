package app.controller;

import app.entity.Category;
import app.entity.Post;
import app.service.CategoryService;
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
public class CategoryController {

    private final CategoryService categoryService;
    private final PostService postService;

    public CategoryController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @RequestMapping("/{category}")
    public String listCategory(Model model, @PathVariable("category") String category){
        return categoryPost(model, category, 1);
    }

    @GetMapping("/{category}/page/{currentPage}")
    public String categoryPost(Model model,
                               @PathVariable("category") String category,
                               @PathVariable("currentPage") int currentPage){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.format(formatter);
        Page<Post> page = postService.listByCategory(category.toLowerCase(), currentPage);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Integer> listTotalPages = IntStream.rangeClosed(1, page.getTotalPages()).boxed().collect(Collectors.toList());
        List<Post> all = page.getContent();
        List<Category> categories = categoryService.allCategory();
        model.addAttribute("path", category);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listTotalPages", listTotalPages);
        model.addAttribute("date", date);
        model.addAttribute("posts", all);
        model.addAttribute("categories", categories);
        return "category";
    }
}
