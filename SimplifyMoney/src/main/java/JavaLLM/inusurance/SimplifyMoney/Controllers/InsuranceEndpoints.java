package JavaLLM.inusurance.SimplifyMoney.Controllers;

import JavaLLM.inusurance.SimplifyMoney.Enitity.InsuranceEntity;
import JavaLLM.inusurance.SimplifyMoney.Enitity.Purchase;

import JavaLLM.inusurance.SimplifyMoney.Enitity.UserEntity;
import JavaLLM.inusurance.SimplifyMoney.Repositories.UserRepo;
import JavaLLM.inusurance.SimplifyMoney.Services.InsuranceService;
import JavaLLM.inusurance.SimplifyMoney.Services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/SimplifyMoney")
public class InsuranceEndpoints {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserRepo userRepo;


    @PostMapping("/AddNewInsurance")
    public ResponseEntity<?> addNewInsurance(@RequestBody InsuranceEntity insurance) {
        insuranceService.saveInsurance(insurance);
        return ResponseEntity.status(HttpStatus.CREATED).body("Insurance Policies have been added successfully");
    }

    @GetMapping("/getAllinsurances")
    public ResponseEntity<?> getAllInsurances() {
        List<InsuranceEntity> lst=insuranceService.getAllInsurances();
        if(lst.size()==0){
            return   ResponseEntity.status(HttpStatus.NO_CONTENT).body("No insurance Policies are Available! Sorry for the inconvience");

        }
      return   ResponseEntity.status(HttpStatus.OK).body(lst);
    }
//@PostMapping("/purchaseInsurance")
//    public ResponseEntity<?> PurachaseInsuarance(@RequestParam Long userId, @RequestParam Long Insuranceid){
//
//
//if(purchaseService.makePurchase(userId,Insuranceid)=="Insufficient funds"){
//    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient funds, please add more money to your account!");
//
//}
@PostMapping("/purchase")
public ResponseEntity<Map<String, String>> purchaseInsurance(
        @RequestParam Long userId, @RequestParam Long insuranceId) {

    try {
        Map<String, String> response = purchaseService.makePurchase(userId, insuranceId);

        if ("failed".equals(response.get("status"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);

    } catch (FileNotFoundException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("status", "error", "message", "Error generating PDF receipt"));
    }
}

    @GetMapping("/download/{insuranceId}")
    public ResponseEntity<Resource> downloadInsurancePdf(@PathVariable Long insuranceId) throws IOException, MalformedURLException {
        String filePath = "generated_pdfs/insurance_" + insuranceId + ".pdf";
        File file = new File(filePath);

        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        Resource resource = new UrlResource(file.toURI());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .body(resource);
    }




        //TODO: Implement logic to purchase insurance
//        return ResponseEntity.status(HttpStatus.OK).body("Insurance Policies have been purchased successfully");
//
//    }


    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserEntity user){
        if(user ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User Data");
        }

        UserEntity newUser = new UserEntity(
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getGender(),
                user.getIncome(),
                user.getAmount()
        );
         purchaseService.addUser(newUser);

        //TODO: Implement logic to add a new user
        return ResponseEntity.status(HttpStatus.OK).body("User has been added successfully");
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        List<UserEntity> lst=userRepo.findAll();
        if(lst.size()==0){
            return   ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Users are Available! Sorry for the inconvience");

        }
        return   ResponseEntity.status(HttpStatus.OK).body(lst);
    }

    @GetMapping("/getAllPurchases")
    public ResponseEntity<?> getAllPurchases(){
        List<Purchase> lst=purchaseService.getPurchases();
        // TODO: Implement logic to get all purchases
        return ResponseEntity.status(HttpStatus.OK).body(lst);
    }

    @PostMapping("/RecommandInsurance")
    public ResponseEntity<?> recommandInsurance(@RequestParam int age, @RequestParam String gender, @RequestParam int income){
        //TODO: Implement logic to recommend insurance based on user's risk level
        List<InsuranceEntity> lst=insuranceService.recommendInsurance(age, income, gender);
        return ResponseEntity.status(HttpStatus.OK).body(lst );
    }

}
