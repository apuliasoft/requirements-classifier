package com.example.mahout.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "Requirements list", description = "A project reqs list")
public class RequirementList implements Serializable {

    @ApiModelProperty(value = "Requirements list")
    private List<Requirement> requirements;

    public RequirementList() {
        this.requirements = new ArrayList<>();
    }

    public RequirementList(ClassifyRequirementList request) {
        this.requirements = new ArrayList<>();
        for (ClassifyRequirement cr : request.getRequirements()) {
            Requirement r  = new Requirement();
            r.setId(cr.getId());
            r.setText(cr.getText());
            requirements.add(r);
        }
    }

    public RequirementList(MultiRequirementList request, String property) throws Exception {
        this.requirements = new ArrayList<>();
        for (MultiRequirement cr : request.getRequirements()) {
            Requirement r  = new Requirement();
            r.setId(cr.getId());
            r.setText(cr.getText());
            r.setReqDomains(property, cr.getReqDomains(property));
            requirements.add(r);
        }
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }
}
