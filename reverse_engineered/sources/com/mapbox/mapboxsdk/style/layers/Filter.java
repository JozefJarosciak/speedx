package com.mapbox.mapboxsdk.style.layers;

import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.ArrayList;
import java.util.Collections;

public class Filter {

    public static abstract class Statement {
        protected final String operator;

        public abstract Object[] toArray();

        public Statement(String str) {
            this.operator = str;
        }
    }

    private static class CompoundStatement extends Statement {
        private final Statement[] statements;

        CompoundStatement(String str, Statement... statementArr) {
            super(str);
            this.statements = statementArr;
        }

        public Object[] toArray() {
            ArrayList arrayList = new ArrayList(this.statements.length + 1);
            arrayList.add(this.operator);
            for (Statement toArray : this.statements) {
                arrayList.add(toArray.toArray());
            }
            return arrayList.toArray();
        }
    }

    private static class SimpleStatement extends Statement {
        private final String key;
        private final Object[] values;

        SimpleStatement(String str, String str2, Object... objArr) {
            super(str);
            this.key = str2;
            this.values = objArr;
        }

        public Object[] toArray() {
            Object arrayList = new ArrayList(this.values.length + 2);
            arrayList.add(this.operator);
            arrayList.add(this.key);
            Collections.addAll(arrayList, this.values);
            return arrayList.toArray();
        }
    }

    public static Statement all(Statement... statementArr) {
        return new CompoundStatement("all", statementArr);
    }

    public static Statement any(Statement... statementArr) {
        return new CompoundStatement("any", statementArr);
    }

    public static Statement none(Statement... statementArr) {
        return new CompoundStatement("none", statementArr);
    }

    public static Statement has(String str) {
        return new SimpleStatement("has", str, new Object[0]);
    }

    public static Statement notHas(String str) {
        return new SimpleStatement("!has", str, new Object[0]);
    }

    public static Statement eq(String str, Object obj) {
        return new SimpleStatement("==", str, obj);
    }

    public static Statement neq(String str, Object obj) {
        return new SimpleStatement("!=", str, obj);
    }

    public static Statement gt(String str, Object obj) {
        return new SimpleStatement(SimpleComparison.GREATER_THAN_OPERATION, str, obj);
    }

    public static Statement gte(String str, Object obj) {
        return new SimpleStatement(SimpleComparison.GREATER_THAN_EQUAL_TO_OPERATION, str, obj);
    }

    public static Statement lt(String str, Object obj) {
        return new SimpleStatement(SimpleComparison.LESS_THAN_OPERATION, str, obj);
    }

    public static Statement lte(String str, Object obj) {
        return new SimpleStatement(SimpleComparison.LESS_THAN_EQUAL_TO_OPERATION, str, obj);
    }

    public static Statement in(String str, Object... objArr) {
        return new SimpleStatement("in", str, objArr);
    }

    public static Statement notIn(String str, Object... objArr) {
        return new SimpleStatement("!in", str, objArr);
    }
}
