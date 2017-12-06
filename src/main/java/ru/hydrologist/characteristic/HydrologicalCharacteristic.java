package ru.hydrologist.characteristic;

import ru.hydrologist.objects.ObservationPost;
import ru.hydrologist.ranges.EmpiricRangable;

public class HydrologicalCharacteristic {

    ObservationPost observationPost;
    String NAME;
    String ABBREVIATION;
    String MEASUREMENT;
    EmpiricRangable empiricRange;

    public EmpiricRangable getEmpiricRange() {
        return empiricRange;
    }
}
