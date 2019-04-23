package com.example.mahout.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "Multiclassify requirements list", description = "A project reqs list")
public class MultiRequirementList implements Serializable {

    @ApiModelProperty(value = "Requirements list")
    private List<MultiRequirement> requirements;

    public MultiRequirementList() {
        this.requirements = new ArrayList<>();
    }

    public List<MultiRequirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<MultiRequirement> requirements) {
        this.requirements = requirements;
    }
}

