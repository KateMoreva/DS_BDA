package ru.spbstu.search.entity.entry.enties.vacancy.extra.area;

import ru.spbstu.search.entity.Entity;
import ru.spbstu.search.entity.IEntity;

import java.util.List;

public class AreaEntity extends Entity<Area> {
    public AreaEntity() {
        super(Area.NULL_AREA);
    }

    public AreaEntity(List<Area> treeList) {
        super(treeList, Area.NULL_AREA);
    }

    @Override
    public boolean hasId(String id) {
        if (super.hasId(id)) {
            return true;
        } else {
            for (Area area : toList()) {
                IEntity<Area> areas = area.getAreas();
                if (areas.hasId(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasName(final String name) {
        if (super.hasName(name)) {
            return true;
        } else {
            for (Area area : toList()) {
                IEntity<Area> areas = area.getAreas();
                if (areas.hasName(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Area getById(String id) {
        if (super.hasId(id)) {
            return super.getById(id);
        } else {
            for (Area area : toList()) {
                IEntity<Area> areas = area.getAreas();
                if (areas.hasId(id)) {
                    return areas.getById(id);
                }
            }
        }
        return getNullObject(id);
    }

    @Override
    public Area getByName(String name) {
        if (super.hasName(name)) {
            return super.getByName(name);
        } else {
            for (Area area : toList()) {
                IEntity<Area> areas = area.getAreas();
                if (areas.hasName(name)) {
                    return areas.getByName(name);
                }
            }
        }
        return getNullObject(name);
    }
}
