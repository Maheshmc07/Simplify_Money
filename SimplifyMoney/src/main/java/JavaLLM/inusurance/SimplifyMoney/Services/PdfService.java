package JavaLLM.inusurance.SimplifyMoney.Services;

import JavaLLM.inusurance.SimplifyMoney.Enitity.InsuranceEntity;
import JavaLLM.inusurance.SimplifyMoney.Enitity.UserEntity;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


@Service
public class PdfService {

    private static final String PDF_DIRECTORY = "generated_pdfs/";

    public String generateInsurancePdf(InsuranceEntity insurance, UserEntity user) throws FileNotFoundException {
        // Ensure directory exists
        File directory = new File(PDF_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = PDF_DIRECTORY + "insurance_" + insurance.getId() + ".pdf";
        PdfWriter writer = new PdfWriter(new FileOutputStream(filePath));
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Insurance Purchase Receipt"));
        document.add(new Paragraph("Customer: " + user.getName()));
        document.add(new Paragraph("Insurance Name: " + insurance.getName()));
        document.add(new Paragraph("Type: " + insurance.getType()));
        document.add(new Paragraph("Price: $" + insurance.getPrice()));
        document.add(new Paragraph("Age Limit: " + insurance.getAgeLimit()));
        document.add(new Paragraph("Description: " + insurance.getDescription()));

        document.add(new Paragraph("\nKey Policy Points:"));
        document.add(new Paragraph("1. Coverage includes accidents and medical expenses."));
        document.add(new Paragraph("2. Premiums are payable monthly or annually."));
        document.add(new Paragraph("3. Claims require proof of policy and valid documents."));
        document.add(new Paragraph("4. Policies can be renewed before expiry."));
        document.add(new Paragraph("5. Refunds are subject to cancellation policies."));
        document.add(new Paragraph("6. Exclusions apply to pre-existing conditions."));

        document.close();
        return filePath;
    }
}
