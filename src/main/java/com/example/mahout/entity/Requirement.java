package com.example.mahout.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "Requirement", description = "A project requirement")
public class Requirement implements Serializable {

    @ApiModelProperty(value = "ID of the requirement")
    String id;
    @ApiModelProperty(value = "Requirement type")
    String requirement_type;
    @ApiModelProperty(value = "Text with the requirement information")
    String text;
    @ApiModelProperty(value = "Requirement domains")
    String reqDomains;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequirement_type() {
        return requirement_type;
    }

    public void setRequirement_type(String requirement_type) {
        this.requirement_type = requirement_type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReqDomains() {
        return reqDomains;
    }

    public void setReqDomains(String reqDomains) {
        this.reqDomains = reqDomains;
    }
}
