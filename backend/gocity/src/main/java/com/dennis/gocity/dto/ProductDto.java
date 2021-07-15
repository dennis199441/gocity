package com.dennis.gocity.dto;

import java.util.Date;

public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private CategoryDto category;

    private Date creationDate;

    private Date updateDate;

    private Date lastPurchasedDate;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, CategoryDto category, Date creationDate,
            Date updateDate, Date lastPurchasedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.lastPurchasedDate = lastPurchasedDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDto getCategory() {
        return this.category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getLastPurchasedDate() {
        return this.lastPurchasedDate;
    }

    public void setLastPurchasedDate(Date lastPurchasedDate) {
        this.lastPurchasedDate = lastPurchasedDate;
    }

    @Override
    public String toString() {
        return String.format("Product[id=%d, name='%s', description='%s', category='%s', creationDate='%s']", id, name,
                description, category, creationDate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ProductDto)) {
            return false;
        }

        ProductDto that = (ProductDto) other;

        if ((this.category != null && that.getCategory() == null)
                || (this.category == null && that.getCategory() != null)) {
            return false;
        }

        return this.id == that.getId() && this.name == that.getName() && this.description == that.getDescription()
                && (this.category == null && that.getCategory() == null ? true
                        : this.category.equals(that.getCategory()))
                && this.creationDate == that.getCreationDate() && this.updateDate == that.getUpdateDate()
                && this.lastPurchasedDate == that.getLastPurchasedDate();
    }
}