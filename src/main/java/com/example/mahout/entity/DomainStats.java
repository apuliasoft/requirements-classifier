package com.example.mahout.entity;

import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainStats implements Serializable {

    @ApiModelProperty(value = "Kappa")
    Double kappa;
    @ApiModelProperty(value = "Accuracy")
    Double accuracy;
    @ApiModelProperty(value = "Reliability")
    Double reliability;
    @ApiModelProperty(value = "Reliability standard deviation")
    Double reliability_std_deviation;
    @ApiModelProperty(value = "Weighted precision")
    Double weighted_precision;
    @ApiModelProperty(value = "Weighted recall")
    Double weighted_recall;
    @ApiModelProperty(value = "Weighted F1 score")
    Double weighted_f1_score;
    @ApiModelProperty(value = "Confusion matrix")
    HashMap<String, ConfusionMatrixStats> confusion_matrix;

    public DomainStats() {
        this.confusion_matrix = new HashMap<>();
    }

    public Double getKappa() {
        return kappa;
    }

    public void setKappa(Double kappa) {
        this.kappa = kappa;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Double getReliability() {
        return reliability;
    }

    public void setReliability(Double reliability) {
        this.reliability = reliability;
    }

    public Double getReliability_std_deviation() {
        return reliability_std_deviation;
    }

    public void setReliability_std_deviation(Double reliability_std_deviation) {
        this.reliability_std_deviation = reliability_std_deviation;
    }

    public Double getWeighted_precision() {
        return weighted_precision;
    }

    public void setWeighted_precision(Double weighted_precision) {
        this.weighted_precision = weighted_precision;
    }

    public Double getWeighted_recall() {
        return weighted_recall;
    }

    public void setWeighted_recall(Double weighted_recall) {
        this.weighted_recall = weighted_recall;
    }

    public Double getWeighted_f1_score() {
        return weighted_f1_score;
    }

    public void setWeighted_f1_score(Double weighted_f1_score) {
        this.weighted_f1_score = weighted_f1_score;
    }

    public HashMap<String, ConfusionMatrixStats> getConfusion_matrix() {
        return confusion_matrix;
    }

    public void setConfusion_matrix(HashMap<String, ConfusionMatrixStats> confusion_matrix) {
        this.confusion_matrix = confusion_matrix;
    }
}
