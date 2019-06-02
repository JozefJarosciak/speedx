package com.j256.ormlite.stmt;

protected enum StatementBuilder$WhereOperation {
    FIRST("WHERE ", null),
    AND("AND (", ") "),
    OR("OR (", ") ");
    
    private final String after;
    private final String before;

    private StatementBuilder$WhereOperation(String str, String str2) {
        this.before = str;
        this.after = str2;
    }

    public void appendBefore(StringBuilder stringBuilder) {
        if (this.before != null) {
            stringBuilder.append(this.before);
        }
    }

    public void appendAfter(StringBuilder stringBuilder) {
        if (this.after != null) {
            stringBuilder.append(this.after);
        }
    }
}
