package com.mastercard.api.disbursement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.api.disbursement.model.DisbursementRequestWrapper;
import com.mastercard.api.disbursement.service.DisbursementService;
import com.mastercard.api.disbursement.service.RequestBuilder;
import org.openapitools.client.model.Disbursement;
import org.openapitools.client.model.DisbursementWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

/**
 * Controller for application
 */
@Controller
public class DisbursementController {
    /**
     * Instance of DisbursementService that we autowire for convenience
     */
    @Autowired
    private DisbursementService service;

    /**
     * Index page that displays a form to create disbursements
     * It is pre-populated with a valid request
     * Visit localhost:8080 to view
     * @param model Spring model for adding attributes
     * @return Index page
     */
    @GetMapping("/")
    public String index(Model model) {
        DisbursementRequestWrapper disbursementRequestWrapper = RequestBuilder.createPrefilledWrapper();
        List<String> recipientUriSchemes = new LinkedList<>();
        List<String> senderUriSchemes = new LinkedList<>();
        recipientUriSchemes.add("PAN"); // For this reference application, we'll only be working with PAN.
        senderUriSchemes.add("PAN");
        senderUriSchemes.add(""); //Sender Uri is not a required field
        model.addAttribute("recipientUriSchemes", recipientUriSchemes);
        model.addAttribute("senderUriSchemes", senderUriSchemes);
        model.addAttribute("disbursementRequestWrapper", disbursementRequestWrapper);
        return "index";
    }

    /**
     * Handles form submission. Calls makeCall using the DisbursementRequestWrapper, which initiates the chain of calls needed
     * to make the call. Will cause an error/success message to be displayed, along with the accompanying request + response.
     * @param disbursementRequestWrapper Instance of DisbursementRequestWrapper that is bound to the form being submitted
     * @param redirectAttributes For holding onto information
     * @return
     * @throws Exception
     */
    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute("disbursementRequestWrapper") DisbursementRequestWrapper disbursementRequestWrapper, RedirectAttributes redirectAttributes) throws Exception{
        DisbursementWrapper disbursementWrapper = service.makeCall(disbursementRequestWrapper);

        ObjectMapper mapper = new ObjectMapper();
        redirectAttributes.addFlashAttribute("request", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(service.getPaymentDisbursement()));

        if (disbursementWrapper != null) {
            Disbursement disbursement = disbursementWrapper.getDisbursement();
            redirectAttributes.addFlashAttribute("success", "Success!");
            redirectAttributes.addFlashAttribute("response", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(disbursement));
        }
        else {
            redirectAttributes.addFlashAttribute("error", service.getErrorMessage());
            redirectAttributes.addFlashAttribute("response", service.getError());
            service.setErrorMessage("");
        }
        return "redirect:/";
    }
}

