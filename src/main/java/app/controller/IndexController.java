package app.controller;

import app.entity.Category;
import app.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    private final CategoryService categoryService;

    public IndexController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getHome(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.format(formatter);
        List<Category> categories = categoryService.allCategory();
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
