package com.mastercard.api.disbursement.service;

import com.mastercard.api.disbursement.model.DisbursementRequestWrapper;
import org.json.JSONObject;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.DisbursementApi;
import org.openapitools.client.model.DisbursementWrapper;
import org.openapitools.client.model.PaymentDisbursement;
import org.openapitools.client.model.PaymentDisbursementWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Class that handles the service side of the application, i.e. making calls to APIs
 */
@Service
public class DisbursementService {
    // Partner ID which is pulled from application.properties
    @Value("${partnerId}")
    private String partnerId;

    // Used to interact with the disbursement portion of the Disbursement API
    @Autowired
    private DisbursementApi disbursementApi;

    // Most recent payment disbursement  made
    private PaymentDisbursement paymentDisbursement;

    // Most recent error response
    private String error;

    // Message to accompany most recent error response
    private String errorMessage = "";

    @Autowired
    public DisbursementService() { }

    /**
     * Takes a DisbursementRequestWrapper, and uses it to make an API call which will create a disbursement
     * Uses createDisbursementRequest to get the data in the form needed
     * @param disbursementRequestWrapper DisbursementRequestWrapper instance, that contains all data needed to construct request
     * @return Instance of DisbursementWrapper if the call was made successfully, or null otherwise
     */
    public DisbursementWrapper makeCall(DisbursementRequestWrapper disbursementRequestWrapper) {
        paymentDisbursement = RequestBuilder.createDisbursementRequest(disbursementRequestWrapper);
        PaymentDisbursementWrapper paymentDisbursementWrapper = new PaymentDisbursementWrapper();
        paymentDisbursementWrapper.setPaymentDisbursement(paymentDisbursement);

        try {
            return disbursementApi.sendDisbursement(partnerId, paymentDisbursementWrapper);
        } catch (ApiException e) {
            JSONObject json = new JSONObject(e.getResponseBody()).getJSONObject("Errors").getJSONArray("Error").getJSONObject(0);
            errorMessage = "Error creating payment";
            error = json.toString(4);
        }
        return null;
    }

    /**
     * Returns most recent PaymentDisbursement
     * @return paymentDisbursement
     */
    public PaymentDisbursement getPaymentDisbursement() {
        return paymentDisbursement;
    }

    /**
     * Returns most recent error response body
     * @return error response
     */
    public String getError() {
        return error;
    }

    /**
     * Returns most recent error message
     * @return error message
     */
    public String getErrorMessage(){
        return errorMessage;
    }

    /**
     * Sets the errorMessage. Mostly used to reset the error message after it is used in the controller
     * @param newErrorMessage string containing new error message
     */
    public void setErrorMessage(String newErrorMessage) {
        errorMessage = newErrorMessage;
    }
}
