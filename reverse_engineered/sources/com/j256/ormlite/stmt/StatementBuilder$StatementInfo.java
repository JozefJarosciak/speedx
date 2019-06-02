package com.j256.ormlite.stmt;

import java.util.List;

public class StatementBuilder$StatementInfo {
    private final List<ArgumentHolder> argList;
    private final String statement;

    private StatementBuilder$StatementInfo(String str, List<ArgumentHolder> list) {
        this.argList = list;
        this.statement = str;
    }

    public String getStatement() {
        return this.statement;
    }

    public List<ArgumentHolder> getArgList() {
        return this.argList;
    }
}
