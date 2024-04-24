package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserDao UserDAO;

    public UserController(UserDao userDao){
        this.UserDAO = userDao;
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("users", UserDAO.index());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String indexCount(@PathVariable ("id") int id,Model model) {
        model.addAttribute("user",UserDAO.indexCount(id));
        return "users/indexCount";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user",new User());
        return "users/NewUser";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("user")User user){
        UserDAO.save(user);
        return "redirect:/users/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", UserDAO.indexCount(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,@PathVariable("id") int id){
        UserDAO.update(id,user);
        return "redirect:/users/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        UserDAO.delete(id);
        return "redirect:/users/index";
    }

}
