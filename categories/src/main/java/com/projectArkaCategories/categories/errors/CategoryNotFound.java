package com.projectArkaCategories.categories.errors;

import org.springframework.web.server.ResponseStatusException;

public class CategoryNotFound extends ResponseStatusException {
  public CategoryNotFound(Long id) {
    super(HttpStatus.NOT_FOUND, "Error: Category with id "+ id + " not found");
  }
}
