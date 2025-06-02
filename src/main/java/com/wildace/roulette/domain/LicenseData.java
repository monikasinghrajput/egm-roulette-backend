package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "licenseData")
public class LicenseData {

    @Id
    private String id;  // Optional MongoDB document ID

    private String name = "";
    private String client = "";
    private String install = "2023-01-31";
    private List<String> macs;
    private boolean validProductCode = true;
    private boolean validProfitCode = true;
    private boolean toBeExpired = false;
    private String productCode = "2509330773";
    private String profitCode = "4260441857";

    // Default constructor
    public LicenseData() {}

    // Parameterized constructor
    public LicenseData(String name,
                       String client,
                       String install,
                       List<String> macs,
                       boolean validProductCode,
                       boolean validProfitCode,
                       boolean toBeExpired,
                       String productCode,
                       String profitCode) {
        this.name = name;
        this.client = client;
        this.install = install;
        this.macs = macs;
        this.validProductCode = validProductCode;
        this.validProfitCode = validProfitCode;
        this.toBeExpired = toBeExpired;
        this.productCode = productCode;
        this.profitCode = profitCode;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getInstall() {
        return install;
    }

    public void setInstall(String install) {
        this.install = install;
    }

    public List<String> getMacs() {
        return macs;
    }

    public void setMacs(List<String> macs) {
        this.macs = macs;
    }

    public boolean isValidProductCode() {
        return validProductCode;
    }

    public void setValidProductCode(boolean validProductCode) {
        this.validProductCode = validProductCode;
    }

    public boolean isValidProfitCode() {
        return validProfitCode;
    }

    public void setValidProfitCode(boolean validProfitCode) {
        this.validProfitCode = validProfitCode;
    }

    public boolean isToBeExpired() {
        return toBeExpired;
    }

    public void setToBeExpired(boolean toBeExpired) {
        this.toBeExpired = toBeExpired;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProfitCode() {
        return profitCode;
    }

    public void setProfitCode(String profitCode) {
        this.profitCode = profitCode;
    }

    @Override
    public String toString() {
        return "LicenseData{" +
                "name='" + name + '\'' +
                ", client='" + client + '\'' +
                ", install='" + install + '\'' +
                ", macs=" + macs +
                ", validProductCode=" + validProductCode +
                ", validProfitCode=" + validProfitCode +
                ", toBeExpired=" + toBeExpired +
                ", productCode='" + productCode + '\'' +
                ", profitCode='" + profitCode + '\'' +
                '}';
    }
}
