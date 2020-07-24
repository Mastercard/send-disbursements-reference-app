package com.mastercard.api.disbursement;

import com.mastercard.api.disbursement.model.DisbursementRequestWrapper;
import com.mastercard.api.disbursement.service.RequestBuilder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@PropertySource("classpath:application.properties")
public class DisbursementReferenceApplicationTests {
    @Autowired
    private MockMvc mvc;

    @org.junit.Test
    public void testCreatePaymentSuccess() throws Exception {
        DisbursementRequestWrapper disbursementRequestWrapper = RequestBuilder.createPrefilledWrapper();
        mvc.perform(post("/submitForm").flashAttr("disbursementRequestWrapper", disbursementRequestWrapper)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.flash().attribute("success", "Success!"))
                .andExpect(redirectedUrl("/"));

    }
    @org.junit.Test
    public void testCreatePaymentFailure() throws Exception {
        DisbursementRequestWrapper disbursementRequestWrapper = RequestBuilder.createPrefilledWrapper();
        disbursementRequestWrapper.setSenderUriIdentifier("3333432233"); // some invalid value
        mvc.perform(post("/submitForm").flashAttr("disbursementRequestWrapper", disbursementRequestWrapper)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.flash().attribute("error", "Error creating payment"))
                .andExpect(redirectedUrl("/"));
    }
}
