package JavaLLM.inusurance.SimplifyMoney.Enitity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Version  // Add this field for optimistic locking
    private Long version;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private int age;
    private String gender;
    private double income;
    private double amount;

    @OneToMany(mappedBy = "userEntity" ,fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Purchase> purchases;

    public UserEntity() {}

    public UserEntity(String name, String email, int age, String gender, double income, double amount) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.income = income;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
