package app.controller;

import app.entity.Category;
import app.service.CategoryService;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final CategoryService categoryService;

    public LoginController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getLogin(Model model){
        List<Category> categories = categoryService.allCategory();
        model.addAttribute("categories", categories);
        return "login";
    }

    @PostMapping
    public String postLogin(@RequestParam("email") String email,
                            @RequestParam("password") String password){


        return "index";
    }
}
