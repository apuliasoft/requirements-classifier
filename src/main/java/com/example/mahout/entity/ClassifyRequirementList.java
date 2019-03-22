package com.example.mahout.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "Classify requirements list", description = "A project reqs list")
public class ClassifyRequirementList implements Serializable {

    @ApiModelProperty(value = "Requirements list")
    private List<ClassifyRequirement> requirements;

    public ClassifyRequirementList() {
        this.requirements = new ArrayList<>();
    }

    public List<ClassifyRequirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<ClassifyRequirement> requirements) {
        this.requirements = requirements;
    }
}
