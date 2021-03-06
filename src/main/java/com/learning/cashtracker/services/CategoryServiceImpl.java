package com.learning.cashtracker.services;

import com.learning.cashtracker.entity.Category;
import com.learning.cashtracker.exceptions.EtBadRequestException;
import com.learning.cashtracker.exceptions.EtResourceNotFoundException;
import com.learning.cashtracker.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository repository;

    @Override
    public List<Category> fetchAllCategories(Integer userId) {
        return repository.findAll(userId);
    }

    @Override
    public Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        return repository.findById(userId, categoryId);
    }

    @Override
    public Category addCategory(Integer userId, String title, String description) throws EtBadRequestException {
        int categoryId = repository.create(userId, title, description);
        return repository.findById(userId, categoryId);
    }

    @Override
    public void updateCategory(Integer userId, Integer categoryId, Category category) throws EtBadRequestException {
        repository.update(userId, categoryId, category);
    }

    @Override
    public void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        this.fetchCategoryById(userId, categoryId); //will throw not found if not existing
        repository.removeById(userId, categoryId);
    }
}
