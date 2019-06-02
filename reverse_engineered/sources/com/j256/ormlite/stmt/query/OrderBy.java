package com.j256.ormlite.stmt.query;

public class OrderBy {
    private final boolean ascending;
    private final String columnName;

    public OrderBy(String str, boolean z) {
        this.columnName = str;
        this.ascending = z;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public boolean isAscending() {
        return this.ascending;
    }
}
