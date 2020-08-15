package app.controller;


import app.entity.Category;
import app.entity.User;
import app.exception.UserAlreadyExistException;
import app.service.CategoryService;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;
    private final CategoryService categoryService;

    public RegisterController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getRegister(Model model){
        List<Category> categories = categoryService.allCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("user", new User());
        return "register";

    }

    @PostMapping
    public String saveRegister(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        if (userService.isRegistered(user.getEmail())){
            throw new UserAlreadyExistException("This email already used");
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
}
