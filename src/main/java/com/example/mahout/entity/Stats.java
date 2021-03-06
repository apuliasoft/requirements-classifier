package com.example.mahout.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "Stats", description = "Stats results of the classifier test")
public class Stats implements Serializable {

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
    @ApiModelProperty(value = "True positives")
    Integer true_positives;
    @ApiModelProperty(value = "False positives")
    Integer false_positives;
    @ApiModelProperty(value = "False negatives")
    Integer false_negatives;
    @ApiModelProperty(value = "True negatives")
    Integer true_negatives;

    public Stats(Double kappa, Double accuracy, Double reliability, Double reliability_std_deviation, Double weighted_precision,
                 Double weighted_recall, Double weighted_f1_score, Integer true_positives, Integer false_positives,
                 Integer false_negatives, Integer true_negatives) {
        this.kappa = kappa;
        this.accuracy = accuracy;
        this.reliability = reliability;
        this.reliability_std_deviation = reliability_std_deviation;
        this.weighted_precision = weighted_precision;
        this.weighted_recall = weighted_recall;
        this.weighted_f1_score = weighted_f1_score;
        this.true_positives = true_positives;
        this.false_positives = false_positives;
        this.false_negatives = false_negatives;
        this.true_negatives = true_negatives;
    }

    public Stats(Double kappa, Double accuracy, Double reliability, Double reliability_std_deviation, Double weighted_precision,
                 Double weighted_recall, Double weighted_f1_score) {
        this.kappa = kappa;
        this.accuracy = accuracy;
        this.reliability = reliability;
        this.reliability_std_deviation = reliability_std_deviation;
        this.weighted_precision = weighted_precision;
        this.weighted_recall = weighted_recall;
        this.weighted_f1_score = weighted_f1_score;
    }

    public Stats() {

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

    public Integer getTrue_positives() {
        return true_positives;
    }

    public void setTrue_positives(Integer true_positives) {
        this.true_positives = true_positives;
    }

    public Integer getFalse_positives() {
        return false_positives;
    }

    public void setFalse_positives(Integer false_positives) {
        this.false_positives = false_positives;
    }

    public Integer getFalse_negatives() {
        return false_negatives;
    }

    public void setFalse_negatives(Integer false_negatives) {
        this.false_negatives = false_negatives;
    }

    public Integer getTrue_negatives() {
        return true_negatives;
    }

    public void setTrue_negatives(Integer true_negatives) {
        this.true_negatives = true_negatives;
    }
}
