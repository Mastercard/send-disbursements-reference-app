package com.mastercard.api.disbursement.model;

/**
 * Class that contains all the required data fields needed to construct
 * a disbursement request
 */
public class DisbursementRequestWrapper {
    private String senderFirstName;
    private String senderLastName;
    private String senderStreet;
    private String senderCity;
    private String senderPostalCode;
    private String senderCountrySubdivision;
    private String senderCountry;
    private String senderUriScheme;
    private String senderUriIdentifier;
    private String senderUriExpYear;
    private String senderUriExpMonth;
    private String senderUriCvc;
    private String recipientFirstName;
    private String recipientLastName;
    private String recipientStreet;
    private String recipientCity;
    private String recipientPostalCode;
    private String recipientCountrySubdivision;
    private String recipientCountry;
    private String recipientUriScheme;
    private String recipientUriIdentifier;
    private String recipientUriExpYear;
    private String recipientUriExpMonth;
    private String recipientUriCvc;
    private String paymentType;
    private String amount;
    private String currency;

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public String getSenderLastName() {
        return senderLastName;
    }

    public void setSenderLastName(String senderLastName) {
        this.senderLastName = senderLastName;
    }

    public String getSenderStreet() {
        return senderStreet;
    }

    public void setSenderStreet(String senderStreet) {
        this.senderStreet = senderStreet;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderPostalCode() {
        return senderPostalCode;
    }

    public void setSenderPostalCode(String senderPostalCode) {
        this.senderPostalCode = senderPostalCode;
    }

    public String getSenderCountrySubdivision() {
        return senderCountrySubdivision;
    }

    public void setSenderCountrySubdivision(String senderCountrySubdivision) {
        this.senderCountrySubdivision = senderCountrySubdivision;
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
    }

    public String getSenderUriScheme() {
        return senderUriScheme;
    }

    public void setSenderUriScheme(String senderUriScheme) {
        this.senderUriScheme = senderUriScheme;
    }

    public String getSenderUriIdentifier() {
        return senderUriIdentifier;
    }

    public void setSenderUriIdentifier(String senderUriIdentifier) {
        this.senderUriIdentifier = senderUriIdentifier;
    }

    public String getSenderUriExpYear() {
        return senderUriExpYear;
    }

    public void setSenderUriExpYear(String senderUriExpYear) {
        this.senderUriExpYear = senderUriExpYear;
    }

    public String getSenderUriExpMonth() {
        return senderUriExpMonth;
    }

    public void setSenderUriExpMonth(String senderUriExpMonth) {
        this.senderUriExpMonth = senderUriExpMonth;
    }

    public String getSenderUriCvc() {
        return senderUriCvc;
    }

    public void setSenderUriCvc(String senderUriCvc) {
        this.senderUriCvc = senderUriCvc;
    }

    public String getRecipientFirstName() {
        return recipientFirstName;
    }

    public void setRecipientFirstName(String recipientFirstName) {
        this.recipientFirstName = recipientFirstName;
    }

    public String getRecipientLastName() {
        return recipientLastName;
    }

    public void setRecipientLastName(String recipientLastName) {
        this.recipientLastName = recipientLastName;
    }

    public String getRecipientUriScheme() {
        return recipientUriScheme;
    }

    public void setRecipientUriScheme(String recipientUriScheme) {
        this.recipientUriScheme = recipientUriScheme;
    }

    public String getRecipientUriIdentifier() {
        return recipientUriIdentifier;
    }

    public void setRecipientUriIdentifier(String recipientUriIdentifier) {
        this.recipientUriIdentifier = recipientUriIdentifier;
    }

    public String getRecipientUriExpYear() {
        return recipientUriExpYear;
    }

    public void setRecipientUriExpYear(String recipientUriExpYear) {
        this.recipientUriExpYear = recipientUriExpYear;
    }

    public String getRecipientUriExpMonth() {
        return recipientUriExpMonth;
    }

    public void setRecipientUriExpMonth(String recipientUriExpMonth) {
        this.recipientUriExpMonth = recipientUriExpMonth;
    }

    public String getRecipientUriCvc() {
        return recipientUriCvc;
    }

    public void setRecipientUriCvc(String recipientUriCvc) {
        this.recipientUriCvc = recipientUriCvc;
    }

    public String getRecipientStreet() {
        return recipientStreet;
    }

    public void setRecipientStreet(String recipientStreet) {
        this.recipientStreet = recipientStreet;
    }

    public String getRecipientCity() {
        return recipientCity;
    }

    public void setRecipientCity(String recipientCity) {
        this.recipientCity = recipientCity;
    }

    public String getRecipientPostalCode() {
        return recipientPostalCode;
    }

    public void setRecipientPostalCode(String recipientPostalCode) {
        this.recipientPostalCode = recipientPostalCode;
    }

    public String getRecipientCountrySubdivision() {
        return recipientCountrySubdivision;
    }

    public void setRecipientCountrySubdivision(String recipientCountrySubdivision) {
        this.recipientCountrySubdivision = recipientCountrySubdivision;
    }

    public String getRecipientCountry() {
        return recipientCountry;
    }

    public void setRecipientCountry(String recipientCountry) {
        this.recipientCountry = recipientCountry;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
