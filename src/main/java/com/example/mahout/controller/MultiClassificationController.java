package com.example.mahout.controller;

import com.example.mahout.entity.*;
import com.example.mahout.entity.siemens.SiemensRequirementList;
import com.example.mahout.service.ClassificationService;
import com.example.mahout.service.DataService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/upc/classifier-component/multiclassifier")
@Api(value = "Facade", produces = MediaType.APPLICATION_JSON_VALUE)
public class MultiClassificationController {

    @Autowired
    private ClassificationService classificationService;

    @PostMapping("/train")
    @ApiOperation(value = "Create multiple property models",
            notes = "Given a list of requirements and a company name, a model is created for each property value of the property PROPERTY-KEY" +
                    " present in the requirements list." +
                    " Each model evaluates if the requirement belongs to the PROPERTY-KEY value or not.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = String.class)})
    public void test(@ApiParam(value = "Request with the requirements to train", required = true) @RequestBody MultiRequirementList request,
                     @ApiParam(value = "Company to which the model belong", required = true) @RequestParam("company") String enterpriseName,
                     @ApiParam(value = "Property to build the multiclassifier")
                         @RequestParam(value = "property-key", required = false, defaultValue = "reqDomains") String propertyKey) throws Exception {
        classificationService.trainByDomain(new RequirementList(request, propertyKey), enterpriseName, propertyKey);
    }

    @PostMapping("/classify")
    @ApiOperation(value = "Classify by domain",
            notes = "Given a list of requirements, a company name and a domain of the company, classifies the list of requirements using" +
                    " the domain model. The result is a list of recommendations based on the classification results.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = RecommendationList.class)})
    public RecommendationList classify(@ApiParam(value = "Request with the requirements to train", required = true) @RequestBody ClassifyRequirementList request,
                                       @ApiParam(value = "Company to which the model belong", required = true) @RequestParam("company") String enterpriseName,
                                       @ApiParam(value = "Domain to classify requirements by", required = true) @RequestParam("property-value") String domain
                         ) throws Exception {
        return classificationService.classifyByDomain(new RequirementList(request), enterpriseName, domain);
    }

    @RequestMapping(value = "train&test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Train and test by domain",
            notes = "Returns the result of k cross-validation using the requirements recieved in the request and the model" +
                    " of the implicit company and PROPERTY-KEY. Splits the requirements in k groups, trains a classifier for each group with " +
                    "all of the requirements recieved except the ones in the group and tests it with the requirements in the group.\n" +
                    "Returns the average of several statistics like the accuracy of the model\n")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = DomainStats.class)})
    public DomainStats trainAndTest(@ApiParam(value = "Request with the requirements to test", required = true) @RequestBody MultiRequirementList request,
                                    @ApiParam(value = "Number of tests", required = true) @RequestParam("k") int n,
                                    @ApiParam(value = "Property to build the multiclassifier")
                                        @RequestParam(value = "property-key", required = false, defaultValue = "reqDomains") String propertyKey) throws Exception {
        System.out.println("Starting train and test functionality");
        DomainStats result = classificationService.trainAndTestByDomain(new RequirementList(request, propertyKey), n, propertyKey);
        return result;

    }

}
