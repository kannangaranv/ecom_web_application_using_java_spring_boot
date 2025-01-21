package org.kannangaranv.ecom_proj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private String brand;
    @JsonProperty
    private BigDecimal price;
    @JsonProperty
    private String category;
    @JsonProperty
    private Date releaseDate;
    @JsonProperty
    private boolean productAvailable;
    @JsonProperty
    private int stockQuantity;

    @JsonProperty
    private String imageName;
    @JsonProperty
    private String imageType;

    @JsonProperty
    @Lob
    private byte[] imageDate;

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public void setImageDate(byte[] imageDate) {
        this.imageDate = imageDate;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public byte[] getImageDate() {
        return imageDate;
    }


}
