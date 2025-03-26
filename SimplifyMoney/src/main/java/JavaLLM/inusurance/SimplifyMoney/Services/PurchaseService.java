package JavaLLM.inusurance.SimplifyMoney.Services;

import JavaLLM.inusurance.SimplifyMoney.Enitity.InsuranceEntity;
import JavaLLM.inusurance.SimplifyMoney.Enitity.Purchase;
import JavaLLM.inusurance.SimplifyMoney.Enitity.UserEntity;
import JavaLLM.inusurance.SimplifyMoney.Repositories.InsuranceRepo;
import JavaLLM.inusurance.SimplifyMoney.Repositories.PurachaseRepo;
import JavaLLM.inusurance.SimplifyMoney.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseService {

    @Autowired
    private InsuranceRepo insuranceRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PurachaseRepo purachaseRepo;



    @Autowired
    private PdfService pdfService;



    @Transactional
    public UserEntity addUser(UserEntity user) {
        // Ensure the user is a new entity
        if (user.getId() != null) {
            throw new IllegalArgumentException("Cannot add an existing user");
        }
        return userRepo.save(user);
    }





    public String makePurchase1(Long userId, Long insuranceId) {

        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        InsuranceEntity insurance = insuranceRepo.findById(insuranceId)
                .orElseThrow(() -> new RuntimeException("Insurance not found"));

        if (userEntity.getAmount() < insurance.getPrice()) {
            return "Insufficient funds";
        }

        // Deduct amount and save updated user
        userEntity.setAmount(userEntity.getAmount() - insurance.getPrice());
        userRepo.save(userEntity); // Missing in your code

        // Create purchase record
        Purchase purchase = new Purchase();
        purchase.setInsurance(insurance);
        purchase.setUserEntity(userEntity);
        purchase.setStatus("Successful");

        purachaseRepo.save(purchase); // Fixed spelling issue

        return "Success";
    }

    public List<Purchase> getPurchases() {
        return purachaseRepo.findAll();
    }


    public Map<String, String> makePurchase(Long userId, Long insuranceId) throws FileNotFoundException {

        // Fetch user
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch insurance
        InsuranceEntity insurance = insuranceRepo.findById(insuranceId)
                .orElseThrow(() -> new RuntimeException("Insurance not found"));

        // Check if user has sufficient balance
        if (userEntity.getAmount() < insurance.getPrice()) {
            return Map.of("status", "failed", "message", "Insufficient funds");
        }

        // Deduct amount and save updated user balance
        userEntity.setAmount(userEntity.getAmount() - insurance.getPrice());
        userRepo.save(userEntity);

        // Create and save the purchase record
        Purchase purchase = new Purchase();
        purchase.setInsurance(insurance);
        purchase.setUserEntity(userEntity);
        purchase.setStatus("Successful");

        purachaseRepo.save(purchase);

        // Generate PDF receipt for the insurance purchase
        String pdfPath = pdfService.generateInsurancePdf(insurance, userEntity);

        // Return structured response with a download link
        return Map.of(
                "status", "success",
                "message", "Insurance purchased successfully",
                "userId", userId.toString(),
                "insuranceId", insuranceId.toString(),
                "remainingBalance", String.valueOf(userEntity.getAmount()),
                "downloadLink", "/insurance/download/" + insuranceId
        );
    }

}
