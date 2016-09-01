package com.tsystems.tara.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.List;

/**
 * Created by arutz on 19.08.2016.
 */
@Entity
public class Vehicle {

    @Id
    private long id;
    private int number;
    private String type;
    private int state;
    private List<String> codeList;
    private int objectId;
    private Date touched;
    private int lfdNr;
    private String variant;
    private String approvalNumber;
    private Date approvalTime;
    private String productionNumber;
    private int clientNumber;
    private String bmVehicle;
    private String bmMotor;
    private int lvaId;
    private String codeLack;
    private int stat1;
    private int stat0;
    private int stat2;
    private int stat3;
    private int cocState;
    private float mass;
    private float massTire1;
    private float massTire2;
    private String countryCode;
    private String engineSerial;

    public Vehicle() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public Date getTouched() {
        return touched;
    }

    public void setTouched(Date touched) {
        this.touched = touched;
    }

    public int getLfdNr() {
        return lfdNr;
    }

    public void setLfdNr(int lfdNr) {
        this.lfdNr = lfdNr;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getProductionNumber() {
        return productionNumber;
    }

    public void setProductionNumber(String productionNumber) {
        this.productionNumber = productionNumber;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getBmVehicle() {
        return bmVehicle;
    }

    public void setBmVehicle(String bmVehicle) {
        this.bmVehicle = bmVehicle;
    }

    public String getBmMotor() {
        return bmMotor;
    }

    public void setBmMotor(String bmMotor) {
        this.bmMotor = bmMotor;
    }

    public int getLvaId() {
        return lvaId;
    }

    public void setLvaId(int lvaId) {
        this.lvaId = lvaId;
    }

    public String getCodeLack() {
        return codeLack;
    }

    public void setCodeLack(String codeLack) {
        this.codeLack = codeLack;
    }

    public int getStat1() {
        return stat1;
    }

    public void setStat1(int stat1) {
        this.stat1 = stat1;
    }

    public int getStat0() {
        return stat0;
    }

    public void setStat0(int stat0) {
        this.stat0 = stat0;
    }

    public int getStat2() {
        return stat2;
    }

    public void setStat2(int stat2) {
        this.stat2 = stat2;
    }

    public int getStat3() {
        return stat3;
    }

    public void setStat3(int stat3) {
        this.stat3 = stat3;
    }

    public int getCocState() {
        return cocState;
    }

    public void setCocState(int cocState) {
        this.cocState = cocState;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public float getMassTire1() {
        return massTire1;
    }

    public void setMassTire1(float massTire1) {
        this.massTire1 = massTire1;
    }

    public float getMassTire2() {
        return massTire2;
    }

    public void setMassTire2(float massTire2) {
        this.massTire2 = massTire2;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getEngineSerial() {
        return engineSerial;
    }

    public void setEngineSerial(String engineSerial) {
        this.engineSerial = engineSerial;
    }
}
