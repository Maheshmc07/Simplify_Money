package JavaLLM.inusurance.SimplifyMoney.Enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private InsuranceEntity insurance;

    private LocalDateTime purchaseTime;

    public Purchase( Long id, InsuranceEntity insurance, String status, UserEntity userEntity) {

        this.id = id;
        this.insurance = insurance;
        this.status = status;
        this.userEntity = userEntity;
    }

    private String status;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InsuranceEntity getInsurance() {
        return insurance;
    }

    public void setInsurance(InsuranceEntity insurance) {
        this.insurance = insurance;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @PrePersist
    protected void onCreate() {
        purchaseTime = LocalDateTime.now();
    }
    public Purchase() {
    }
}
