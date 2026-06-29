package com.eats.zippyeatsapp;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;



@RestController
@CrossOrigin("*")

public class maincontroller {
    int count;
    @Autowired
    UserRepository repo;
    @PostMapping("/signup")
    public String signup(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String confirmpassword) {
        user existing = repo.findByEmail(email);
        if(existing != null){
            return "Mail already exist";
        }
        user u = new user();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(password);
        u.setConfirmpassword(confirmpassword);
        repo.save(u);
        return "Signup success";
    }
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        user user = repo.findByEmail(email);
        if(user == null){
            return "User not found";
        }
        if(user.getPassword().equals(password)){
            return "success";
        }
        return "Invalid password";
    }
   @Autowired
   MenuRepository menurepo;
   @GetMapping("/add")
   public menu add(@RequestParam String image, @RequestParam String name, @RequestParam String price) {
      menu m = new menu();
      m.setImage(image);
      m.setName(name);
      m.setPrice(price);
      return menurepo.save(m);
   }
   @GetMapping("/menus")
   public List<menu> getMenu() {
       return menurepo.findAll();
   }
   
   @GetMapping("/detailscount")
   public long detailcount() {
       return menurepo.count();
   }
   
   
   
   
    
    
    
}
