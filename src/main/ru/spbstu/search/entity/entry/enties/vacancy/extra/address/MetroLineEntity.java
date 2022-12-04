package ru.spbstu.search.entity.entry.enties.vacancy.extra.address;

import ru.spbstu.search.entity.Entity;
import ru.spbstu.search.entity.IEntity;

import java.util.List;

public class MetroLineEntity extends Entity<MetroLine> {
    public MetroLineEntity() {
        super(MetroStation.NULL_METRO_STATION);
    }

    public MetroLineEntity(List<MetroLine> metroLines) {
        super(MetroStation.NULL_METRO_STATION);
        for (MetroLine line : metroLines) {
            addEntry(line);
        }
    }

    @Override
    public boolean hasId(String id) {
        if (super.hasId(id)) {
            return true;
        } else {
            for (MetroLine line : toList()) {
                IEntity<MetroStation> stations = line.getStations();
                if (stations.hasId(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasName(String name) {
        if (super.hasName(name)) {
            return true;
        } else {
            for (MetroLine line : toList()) {
                IEntity<MetroStation> stations = line.getStations();
                if (stations.hasName(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public MetroLine getById(String id) {
        if (hasId(id)) {
            if (super.hasId(id)) {
                return super.getById(id);
            } else {
                for (MetroLine line : toList()) {
                    IEntity<MetroStation> stations = line.getStations();
                    if (stations.hasId(id)) {
                        return stations.getById(id);
                    }
                }
            }
        }
        return getNullObject(id);
    }

    @Override
    public MetroLine getByName(String name) {
        if (hasName(name)) {
            if (super.hasName(name)) {
                return super.getByName(name);
            } else {
                for (MetroLine line : toList()) {
                    IEntity<MetroStation> stations = line.getStations();
                    if (stations.hasName(name)) {
                        return stations.getByName(name);
                    }
                }
            }
        }
        return getNullObject(name);
    }
}
