package org.example.dijanira.controller;

import org.example.dijanira.dao.CategoryManager;
import org.example.dijanira.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryManager categoryManager;


    @GetMapping
    public String getAllCategories(Model model) {
        List<Category> categories = categoryManager.getAll();
        model.addAttribute("categories", categories);
        return "page/ListCategory";
    }


    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category("", 0.0));
        return "page/AddCategory";
    }

    // Processar formulário para adicionar categoria
    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryManager.addCategory(category);
        return "redirect:/home";
    }


    @GetMapping("/edit/{cname}")
    public String showEditCategoryForm(@PathVariable String cname, Model model) {
        List<Category> categories = categoryManager.getAll();
        Category category = categories.stream()
                .filter(c -> c.getCname().equals(cname))
                .findFirst()
                .orElse(null);

        model.addAttribute("category", category);
        return "categories/EditCategory";
    }


    @PostMapping("/edit")
    public String updateCategory(@ModelAttribute Category category) {
        categoryManager.updateCategory(category);
        return "redirect:/categories";
    }


    @GetMapping("/delete/{cname}")
    public String deleteCategory(@PathVariable String cname) {
        categoryManager.deleteCategory(cname);
        return "redirect:/categories";
    }
}
