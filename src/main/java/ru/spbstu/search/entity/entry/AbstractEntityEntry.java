package ru.spbstu.search.entity.entry;

import static java.lang.String.format;

public abstract class AbstractEntityEntry implements IEntityEntry {
    private static final String NULL_ID = "IdForNullEntries";
    private static final String NULL_NAME = "NameForNullEntries";
    private String id;
    private String name;

    public AbstractEntityEntry() {
        this(NULL_ID, NULL_NAME);
    }

    public AbstractEntityEntry(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isNull() {
        return (getId() == null
                || getId().isEmpty()
                || NULL_ID.equalsIgnoreCase(getId())
                || NULL_NAME.equalsIgnoreCase(getName()));
    }

    @Override
    public boolean equals(Object otherObj) {
        if (!(otherObj instanceof AbstractEntityEntry)) {
            return false;
        }
        AbstractEntityEntry other = (AbstractEntityEntry) otherObj;
        return getId().equalsIgnoreCase(other.getId());
    }

    @Override
    public String toString() {
        return format("%s(%s)", getName(), getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
