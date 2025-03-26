package JavaLLM.inusurance.SimplifyMoney.Enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data

public class InsuranceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private double price;
    private String description;
    private int ageLimit;
    private String gender;

    @Version
    private Long version; // Add version for optimistic locking

    @OneToMany(mappedBy = "insurance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Purchase> purchases;

    private int minincome;

    // Constructor with all fields
    public InsuranceEntity(int ageLimit, String description, String gender, Long id,
                           int minincome, String name, double price,
                           List<Purchase> purchases, String type) {
        this.ageLimit = ageLimit;
        this.description = description;
        this.gender = gender;
        this.id = id;
        this.minincome = minincome;
        this.name = name;
        this.price = price;
        this.purchases = purchases;
        this.type = type;
    }
    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getGender() {
        return gender;
    }
    public int getMinincome() {
        return minincome;
    }

    public void setMinincome(int minincome) {
        this.minincome = minincome;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public InsuranceEntity() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
