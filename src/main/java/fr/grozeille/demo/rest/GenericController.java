package fr.grozeille.demo.rest;

import com.atlassian.oai.validator.report.ValidationReport;
import com.atlassian.oai.validator.springmvc.HttpServletRequestValidationService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class GenericController {

    @GetMapping("/report")
    public String report(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ClassPathResource classPathResource = new ClassPathResource("/swagger.json");
        HttpServletRequestValidationService requestValidationService = new HttpServletRequestValidationService(new EncodedResource(classPathResource));

        ValidationReport validationReport = requestValidationService.validateRequest(request);
        if(validationReport.hasErrors()){
            throw new Exception(validationReport.toString());
        }

        return "Success";
    }
}
