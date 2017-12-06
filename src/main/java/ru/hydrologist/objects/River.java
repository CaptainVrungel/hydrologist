package ru.hydrologist.objects;

public class River extends HydrologicalObject{
    Double length;
    Double catchementArea;

    River(String name){
        super(name);
    }

    River(String name, Double length, Double catchementArea){
        super(name);
        this.length = length;
        this.catchementArea = catchementArea;
    }

    public Double getLength() {
        return length;
    }

    public Double getCatchementArea() {
        return catchementArea;
    }
}
