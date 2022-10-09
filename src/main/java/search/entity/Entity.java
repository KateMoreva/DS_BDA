package search.entity;

import lombok.extern.slf4j.Slf4j;
import search.entity.entry.IEntityEntry;

import java.util.*;

import static java.lang.String.format;

@Slf4j
public class Entity<V extends IEntityEntry> implements IEntity<V> {
    private Map<String, V> idMap = new HashMap<>();
    private Map<String, V> nameMap = new HashMap<>();
    private V nullObject;

    public Entity(V nullObject) {
        this.nullObject = nullObject;
    }

    public Entity(List<V> entryList, V nullObject) {
        for (V entry : entryList) {
            addEntry(entry);
        }
        this.nullObject = nullObject;
    }

    public final void addEntry(V entry) {
        putId(entry);
        putName(entry);
    }

    protected void putName(V entry) {
        if (entry.getName() == null) {
            throw new IllegalArgumentException(format("Name is null (id %s).", entry.getId()));
        }
        String name = entry.getName().toUpperCase();
        if (nameMap.containsKey(name)) {
            log.warn(format("Reputed name: %s.", name));
        }
        nameMap.put(name, entry);
    }

    protected void putId(V entry) {
        if (entry.getId() == null) {
            throw new IllegalArgumentException(format("Key is null (name %s).", entry.getName()));
        }
        String id = entry.getId().toUpperCase();
        if (idMap.containsKey(id)) {
            throw new IllegalArgumentException(format("Reputed key: %s.", id));
        }
        idMap.put(id, entry);
    }

    @Override
    public int size() {
        return idMap.size();
    }

    @Override
    public List<V> toList() {
        return new ArrayList<>(idMap.values());
    }

    @Override
    public Map<String, V> toIdMap() {
        return idMap;
    }

    @Override
    public Map<String, V> toNameMap() {
        return nameMap;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (String key : idMap.keySet()) {
            if (builder.length() != 1) {
                builder.append(",");
            }
            builder.append(getById(key));
        }
        builder.append("}");
        return builder.toString();
    }

    @Override
    public boolean hasId(String id) {
        return idMap.containsKey(id.toUpperCase());
    }

    @Override
    public boolean hasName(String name) {
        return nameMap.containsKey(name.toUpperCase());
    }

    @Override
    public V getById(String id) {
        V entry = idMap.get(id.toUpperCase());
        return (entry != null) ? entry : getNullObject(id);
    }

    @Override
    public V getByName(String name) {
        V entry = nameMap.get(name.toUpperCase());
        return (entry != null) ? entry : getNullObject(name);
    }

    @Override
    public Iterator<V> iterator() {
        return toList().iterator();
    }

    protected V getNullObject(String idOrName) {
        log.warn("Cant find object {}, return NullObject.", idOrName);
        return nullObject;
    }
}