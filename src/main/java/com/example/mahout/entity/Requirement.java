package com.example.mahout.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;

@ApiModel(value = "Requirement", description = "A project requirement")
public class Requirement implements Serializable {

    @ApiModelProperty(value = "ID of the requirement")
    String id;
    @ApiModelProperty(value = "Requirement type")
    String requirement_type;
    @ApiModelProperty(value = "Text with the requirement information")
    String text;

    HashMap<String, String> properties;

    public Requirement() {
        properties = new HashMap<>();
    }

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

    public String getReqDomains(String key) throws Exception {
        String s = properties.get(key);
        if (s == null) throw new Exception("Property not present in JSON object");
        else return s;
    }

    public void setReqDomains(String key, String reqDomains) {
        this.properties.put(key, reqDomains);
    }

    @JsonAnySetter
    public void setUnrecognizedFields(String key, Object value) {
        this.properties.put(key, (String) value);
    }
}
