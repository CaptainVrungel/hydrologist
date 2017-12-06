package ru.hydrologist.objects;

import java.util.Collections;
import java.util.List;

public class HydrologicalObject {

    String name;
    String description;
    List<ObservationPost> observationPosts;

    HydrologicalObject(String name){
        this.name = name;
    }

    HydrologicalObject(String name, String description){
        this.name = name;
        this.description = description;
    }

    void addObservationPost(ObservationPost post){
        observationPosts.add(post);
    }

    void removeObservationPost(ObservationPost post){
        observationPosts.remove(post);
    }

    List<ObservationPost> getObservationPosts(){
        return Collections.unmodifiableList(observationPosts);
    }

}
