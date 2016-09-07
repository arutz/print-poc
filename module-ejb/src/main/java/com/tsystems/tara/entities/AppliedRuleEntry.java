package com.tsystems.tara.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by arutz on 06.09.2016.
 */
@Entity(name = "ruleEntry")
@Table(name = "ruleEntry")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AppliedRuleEntry {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String processExecutionId;
    private String processExecutionElementId;
    private String ruleName;
    @Lob
    private String input;
    @Lob
    private String contextInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcessExecutionId() {
        return processExecutionId;
    }

    public void setProcessExecutionId(String processExecutionId) {
        this.processExecutionId = processExecutionId;
    }

    public String getProcessExecutionElementId() {
        return processExecutionElementId;
    }

    public void setProcessExecutionElementId(String processExecutionElementId) {
        this.processExecutionElementId = processExecutionElementId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getContextInfo() {
        return contextInfo;
    }

    public void setContextInfo(String contextInfo) {
        this.contextInfo = contextInfo;
    }
}
