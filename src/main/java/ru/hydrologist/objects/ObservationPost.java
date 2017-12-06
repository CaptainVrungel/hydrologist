package ru.hydrologist.objects;

import ru.hydrologist.location.Location;
import ru.hydrologist.ranges.EmpiricRangable;

import java.util.List;

public class ObservationPost {

    String name;
    String code;
    HydrologicalObject hydrologicalObject;
    Location location;
    List<EmpiricRangable> empiricRange;

}
