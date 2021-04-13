package com.learning.cashtracker.resources;

import com.learning.cashtracker.entity.Category;
import com.learning.cashtracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api/categories")
public class CategoryResource {

    @Autowired
    CategoryService service;


    @GetMapping
    public String getAllCategories(HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        return "Authenticated! userId: " + userId;
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(HttpServletRequest request,
                                                @RequestBody Map<String, Object> categoryMap) {
        int userId = (Integer) request.getAttribute("userId");
        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");
        Category category = service.addCategory(userId, title, description);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
}
