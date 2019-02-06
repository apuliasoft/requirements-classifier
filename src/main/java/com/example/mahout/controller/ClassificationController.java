package com.example.mahout.controller;

import com.example.mahout.entity.*;
import com.example.mahout.entity.siemens.SiemensRequirementList;
import com.example.mahout.service.ClassificationService;
import com.example.mahout.service.DataService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upc/classifier-component")
@Api(value = "MahoutAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClassificationController {

    @Autowired
    private DataService dataService;
    @Autowired
    private ClassificationService classificationService;

    /**
     * Just for testing purposes
     * @return
     * @throws Exception
     */
//    @GetMapping("/test")
    public String test() throws Exception {
        return "OK";
    }

    @PostMapping("/train")
    @ApiOperation(value = "Train a model",
            notes = "Given a list of requirements, and a specific company and property, a new model is generated and stored in "
                    + "the database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = String.class)})
    public String train(@ApiParam(value = "Request with the requirements to train", required = true) @RequestBody RequirementList request,
                               @ApiParam(value = "Property of the classifier", required = true) @RequestParam("property") String property,
                               @ApiParam(value = "Company to which the model belong", required = true) @RequestParam("company") String enterpriseName) throws Exception {

        classificationService.train(request, property, enterpriseName);

        return "Train succesfull";
    }

    @RequestMapping(value = "classify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Classify a list of requirements",
            notes = "Given a list of requirements, and using the model stored for the requested company, the requirements are classified " +
            " and a recommended label is returned for each requirement (with a level of confidence)")
    @ApiResponses( value = {@ApiResponse(code = 200, message = "OK", response = RecommendationList.class)})
    public RecommendationList classify(@ApiParam(value = "Request with the requirements to classify", required = true)@RequestBody RequirementList request,
                                              @ApiParam(value = "Property of the classifier", required = true) @RequestParam("property") String property,
                                              @ApiParam(value = "Company to which the model belong", required = true) @RequestParam("company") String enterpriseName) throws Exception {

        return classificationService.classify(request, property, enterpriseName);

    }

    @RequestMapping(value = "train&test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Train and test",
            notes = "Returns the result of k cross-validation using the requirements recieved in the request. Splits the requirements in k groups, trains a classifier for each group with all of the requirements recieved except the ones in the group and tests it with the requirements in the group.\n" +
                    "Returns the average of several statistics like the accuracy of the model\n")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Stats.class)})
    public Stats trainAndTest(@ApiParam(value = "Request with the requirements to test", required = true) @RequestBody RequirementList request,
                                     @ApiParam(value = "Number of tests", required = true) @RequestParam("k") int n) throws Exception {
        System.out.println("Starting train and test functionality");
        /* Parse the body of the request */
//        JSONObject body = new JSONObject(request);

        Stats result = classificationService.trainAndTest(request, n);

        return result;

    }

    @RequestMapping(value = "update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a model",
            notes = "Given a list of requirements, updates the model of the classifier for the given company")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = String.class)})
    public String update(@ApiParam(value = "Request with the requirements to train and update the model", required = true) @RequestBody RequirementList request,
                                @ApiParam(value = "Property of the classifier", required = true) @RequestParam("property") String property,
                                @ApiParam(value = "Company to which the model belong", required = true) @RequestParam("company") String enterpriseName) throws Exception {

        return classificationService.update(request, property, enterpriseName);

    }

    //TODO refactor to DELETE
    @RequestMapping(value = "deleteModel", method = RequestMethod.POST)
    @ApiOperation(value = "Deleted an existing model",
            notes = "Given a company and a property, deletes the associated stored model.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = String.class)})
    public String deleteModel(@RequestBody CompanyPropertyKey request) throws Exception {

        return classificationService.delete(request);

    }

    @RequestMapping(value = "parse", method = RequestMethod.POST)
    public RequirementList parseSiemensToOpenReq(@RequestBody SiemensRequirementList siemensRequirementList) {
        return dataService.parseSiemensToOpenReq(siemensRequirementList);
    }

}
