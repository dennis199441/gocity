package com.dennis.gocity.dto;

public class CategoryDto {

    private Long id;

    private String categoryName;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return String.format("Category[id=%d, categoryName='%s']", id, categoryName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (!(other instanceof CategoryDto))
            return false;
            
        CategoryDto that = (CategoryDto) other;

        return this.id == that.getId() && this.categoryName == that.getCategoryName();
    }
}
