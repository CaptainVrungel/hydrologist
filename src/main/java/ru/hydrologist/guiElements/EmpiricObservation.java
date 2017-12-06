package ru.hydrologist.guiElements;

public class EmpiricObservation {

    private String value;
    private String year;
    private Double probability;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getProbability() {
        return probability;
    }

    public EmpiricObservation(){

    }

    public EmpiricObservation(Double value, Double probability){
        this.value = value.toString();
        this.probability = probability;
    }

    public int compareTo(SingleObservation observation) {

        if(this.probability - observation.probability > 0){
            return -1;
        }else if(this.probability - observation.probability < 0){
            return 1;
        }else{
            return 0;
        }

    }

}
