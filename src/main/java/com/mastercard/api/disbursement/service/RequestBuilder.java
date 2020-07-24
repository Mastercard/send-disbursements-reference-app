package com.mastercard.api.disbursement.service;

import com.mastercard.api.disbursement.model.DisbursementRequestWrapper;
import org.openapitools.client.model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

/**
 * Class in charge of building requests
 */
public class RequestBuilder {
    /**
     * Takes a disbursementRequestWrapper, and unpacks it into the appropriate models that our
     * API client instances want
     * @param disbursementRequestWrapper DisbursementRequestWrapper instance, that contains all data needed to construct request
     * @return Instance of PaymentDisbursement that is ready to be used with an instance of DisbursementAPI to make API calls
     */
    static PaymentDisbursement createDisbursementRequest(DisbursementRequestWrapper disbursementRequestWrapper) {

        PaymentDisbursement paymentDisbursement = new PaymentDisbursement();

        Sender sender = new Sender();

        SenderAddressRequest senderAddressRequest = new SenderAddressRequest();
        senderAddressRequest.setLine1(disbursementRequestWrapper.getSenderStreet());
        senderAddressRequest.setCity(disbursementRequestWrapper.getSenderCity());
        senderAddressRequest.setCountry(disbursementRequestWrapper.getSenderCountry());
        senderAddressRequest.setCountrySubdivision(disbursementRequestWrapper.getSenderCountrySubdivision());
        senderAddressRequest.setPostalCode(disbursementRequestWrapper.getSenderPostalCode());

        sender.setFirstName(disbursementRequestWrapper.getSenderFirstName());
        sender.setLastName(disbursementRequestWrapper.getSenderLastName());
        sender.setAddress(senderAddressRequest);

        Recipient recipient = new Recipient();

        RequestRecipientAddress requestRecipientAddress = new RequestRecipientAddress();
        requestRecipientAddress.setLine1(disbursementRequestWrapper.getRecipientStreet());
        requestRecipientAddress.setCity(disbursementRequestWrapper.getRecipientCity());
        requestRecipientAddress.setCountry(disbursementRequestWrapper.getRecipientCountry());
        requestRecipientAddress.setCountrySubdivision(disbursementRequestWrapper.getRecipientCountrySubdivision());
        requestRecipientAddress.setPostalCode(disbursementRequestWrapper.getRecipientPostalCode());

        recipient.setFirstName(disbursementRequestWrapper.getRecipientFirstName());
        recipient.setLastName(disbursementRequestWrapper.getRecipientLastName());
        recipient.setAddress(requestRecipientAddress);

        // Generating random disbursement reference number
        String disbursementReference = generateDisbursementReference();

        // Grabbing the current time in ISO 8601 format
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String currentMoment = df.format(new Date());

        paymentDisbursement.setSender(sender);
        paymentDisbursement.setRecipient(recipient);

        //Since SenderAccountUri is not required, we only fill the field if it is not empty
        if(disbursementRequestWrapper.getSenderUriIdentifier() != "") {
            paymentDisbursement.setSenderAccountUri("pan:" + disbursementRequestWrapper.getSenderUriIdentifier() + ";exp=" + disbursementRequestWrapper.getSenderUriExpYear()
                    + "-" + disbursementRequestWrapper.getSenderUriExpMonth() + ";cvc=" + disbursementRequestWrapper.getSenderUriCvc());
        }

        paymentDisbursement.setRecipientAccountUri("pan:" + disbursementRequestWrapper.getRecipientUriIdentifier() + ";exp=" + disbursementRequestWrapper.getRecipientUriExpYear()
                + "-" + disbursementRequestWrapper.getRecipientUriExpMonth() + ";cvc=" + disbursementRequestWrapper.getRecipientUriCvc());
        paymentDisbursement.setPaymentType(disbursementRequestWrapper.getPaymentType());
        paymentDisbursement.setAmount(disbursementRequestWrapper.getAmount());
        paymentDisbursement.setCurrency(disbursementRequestWrapper.getCurrency());

        //paymentDisbursement.setAdditionalMessage("Adding message for this payment");
        paymentDisbursement.setDisbursementReference(disbursementReference);
        return paymentDisbursement;
    }

    public static String generateDisbursementReference () {
        Random random = new Random();
        long n = (long) (100000000000000L + random.nextFloat() * 900000000000000L);
        return "DISBURSEMENT_" + n;
    }

    /**
     * Creates an instance of disbursementRequestWrapper with pre-populated fields to
     * allow for fast form submissions.
     * @return Instance of disbursementRequestWrapper class
     */
    public static DisbursementRequestWrapper createPrefilledWrapper() {
        DisbursementRequestWrapper disbursementRequestWrapper = new DisbursementRequestWrapper();
        disbursementRequestWrapper.setSenderFirstName("John");
        disbursementRequestWrapper.setSenderLastName("Jones");
        disbursementRequestWrapper.setSenderStreet("1 Main St");
        disbursementRequestWrapper.setSenderCity("Chicago");
        disbursementRequestWrapper.setSenderPostalCode("63368");
        disbursementRequestWrapper.setSenderCountrySubdivision("MO");
        disbursementRequestWrapper.setSenderCountry("USA");
        disbursementRequestWrapper.setSenderUriScheme("PAN");
        disbursementRequestWrapper.setSenderUriIdentifier("4007589999999953");
        disbursementRequestWrapper.setSenderUriExpYear("2077");
        disbursementRequestWrapper.setSenderUriExpMonth("08");
        disbursementRequestWrapper.setSenderUriCvc("123");
        disbursementRequestWrapper.setRecipientStreet("1 Main Street");
        disbursementRequestWrapper.setRecipientCity("St. Louis");
        disbursementRequestWrapper.setRecipientPostalCode("63368");
        disbursementRequestWrapper.setRecipientCountrySubdivision("MO");
        disbursementRequestWrapper.setRecipientCountry("USA");
        disbursementRequestWrapper.setRecipientFirstName("Jane");
        disbursementRequestWrapper.setRecipientLastName("Smith");
        disbursementRequestWrapper.setRecipientUriScheme("PAN");
        disbursementRequestWrapper.setRecipientUriIdentifier("5299920210000277");
        disbursementRequestWrapper.setRecipientUriExpYear("2099");
        disbursementRequestWrapper.setRecipientUriExpMonth("08");
        disbursementRequestWrapper.setRecipientUriCvc("123");
        disbursementRequestWrapper.setPaymentType("BDB");
        disbursementRequestWrapper.setAmount("1000");
        disbursementRequestWrapper.setCurrency("USD");
        return disbursementRequestWrapper;
    }
}

