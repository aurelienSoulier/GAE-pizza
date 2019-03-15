package com.urca.das.entities;

import java.sql.Timestamp;

public class Pizza {

    private String name;
    private String image;
    private String description;
    
    private Long id;

    public static final String NAME = "name";
    public static final String IMAGE = "image";
    public static final String DESCRIPTION = "description";


//constructor    
    private Pizza(Builder builder) {
        this.name = builder.name;
        this.image = builder.image;
        this.description = builder.description;
        this.id = builder.id;
      }

//BUILDER
      public static class Builder {
        private String name;
        private String image;
        private String description;
        private Long id;
    
        public Builder name(String name) {
          this.name = name;
          return this;
        }
    
        public Builder image(String image) {
          this.image = image;
          return this;
        }
    
        public Builder description(String description) {
          this.description = description;
          return this;
        }

        public Builder id(Long id) {
          this.id = id;
          return this;
        }

        public Pizza build() {
            return new Pizza(this);
      }
    }


//Getter/Setter    
    public String getName() {
        return name;
    }
    public void setName( String name ) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }
    public void setImage( String image ) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription( String description ) {
        this.description = description;
    }

    public long getId() {
        return id;
    }
    public void setId( long id ) {
        this.id = id;
    }
}