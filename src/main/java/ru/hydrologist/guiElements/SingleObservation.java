package ru.hydrologist.guiElements;

public class SingleObservation implements Comparable<SingleObservation>{

    Double value;
    Double probability;

    public SingleObservation(){

    }

    public SingleObservation(Double value, Double probability){
        this.value = value;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }



}
