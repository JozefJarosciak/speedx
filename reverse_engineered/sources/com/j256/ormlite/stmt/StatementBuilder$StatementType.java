package com.j256.ormlite.stmt;

public enum StatementBuilder$StatementType {
    SELECT(true, true, false, false),
    SELECT_LONG(true, true, false, false),
    SELECT_RAW(true, true, false, false),
    UPDATE(true, false, true, false),
    DELETE(true, false, true, false),
    EXECUTE(false, false, false, true);
    
    private final boolean okForExecute;
    private final boolean okForQuery;
    private final boolean okForStatementBuilder;
    private final boolean okForUpdate;

    private StatementBuilder$StatementType(boolean z, boolean z2, boolean z3, boolean z4) {
        this.okForStatementBuilder = z;
        this.okForQuery = z2;
        this.okForUpdate = z3;
        this.okForExecute = z4;
    }

    public boolean isOkForStatementBuilder() {
        return this.okForStatementBuilder;
    }

    public boolean isOkForQuery() {
        return this.okForQuery;
    }

    public boolean isOkForUpdate() {
        return this.okForUpdate;
    }

    public boolean isOkForExecute() {
        return this.okForExecute;
    }
}
