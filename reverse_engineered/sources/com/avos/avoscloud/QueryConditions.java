package com.avos.avoscloud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class QueryConditions {
    private List<String> include = new LinkedList();
    private int limit;
    private String order;
    private Map<String, String> parameters = new HashMap();
    private Set<String> selectedKeys;
    private int skip;
    private boolean trace;
    Map<String, List<QueryOperation>> where = new HashMap();

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int i) {
        this.limit = i;
    }

    public int getSkip() {
        return this.skip;
    }

    public void setSkip(int i) {
        this.skip = i;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String str) {
        this.order = str;
    }

    public List<String> getInclude() {
        return this.include;
    }

    public void setInclude(List<String> list) {
        this.include = list;
    }

    public Set<String> getSelectedKeys() {
        return this.selectedKeys;
    }

    public void setSelectedKeys(Set<String> set) {
        this.selectedKeys = set;
    }

    public Map<String, List<QueryOperation>> getWhere() {
        return this.where;
    }

    public void setWhere(Map<String, List<QueryOperation>> map) {
        this.where = map;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public void setParameters(Map<String, String> map) {
        this.parameters = map;
    }

    public boolean isTrace() {
        return this.trace;
    }

    public void setTrace(boolean z) {
        this.trace = z;
    }

    public void addAscendingOrder(String str) {
        if (AVUtils.isBlankString(this.order)) {
            orderByAscending(str);
            return;
        }
        this.order = String.format("%s,%s", new Object[]{this.order, str});
    }

    public void orderByAscending(String str) {
        this.order = String.format("%s", new Object[]{str});
    }

    public void addDescendingOrder(String str) {
        if (AVUtils.isBlankString(this.order)) {
            orderByDescending(str);
            return;
        }
        this.order = String.format("%s,-%s", new Object[]{this.order, str});
    }

    public void orderByDescending(String str) {
        this.order = String.format("-%s", new Object[]{str});
    }

    public void include(String str) {
        this.include.add(str);
    }

    public void selectKeys(Collection<String> collection) {
        if (this.selectedKeys == null) {
            this.selectedKeys = new HashSet();
        }
        this.selectedKeys.addAll(collection);
    }

    public Map<String, Object> compileWhereOperationMap() {
        Map<String, Object> hashMap = new HashMap();
        for (Entry entry : this.where.entrySet()) {
            List<QueryOperation> list = (List) entry.getValue();
            String str = (String) entry.getKey();
            List list2;
            if (!str.equals(QueryOperation.OR_OP)) {
                switch (list.size()) {
                    case 0:
                        break;
                    case 1:
                        for (QueryOperation toResult : list) {
                            hashMap.put(str, toResult.toResult());
                        }
                        break;
                    default:
                        Collection arrayList = new ArrayList();
                        Map hashMap2 = new HashMap();
                        Object obj = null;
                        for (QueryOperation toResult2 : list) {
                            arrayList.add(toResult2.toResult(str));
                            if (QueryOperation.EQUAL_OP.equals(toResult2.op)) {
                                obj = 1;
                            }
                            if (obj == null) {
                                hashMap2.putAll((Map) toResult2.toResult());
                            }
                        }
                        if (obj == null) {
                            hashMap.put(str, hashMap2);
                            break;
                        }
                        list2 = (List) hashMap.get("$and");
                        if (list2 == null) {
                            hashMap.put("$and", arrayList);
                            break;
                        }
                        list2.addAll(arrayList);
                        break;
                }
            }
            Collection arrayList2 = new ArrayList();
            for (QueryOperation toResult3 : list) {
                arrayList2.add(toResult3.toResult());
            }
            list2 = (List) hashMap.get(QueryOperation.OR_OP);
            if (list2 != null) {
                list2.addAll(arrayList2);
            } else {
                hashMap.put(QueryOperation.OR_OP, arrayList2);
            }
        }
        return hashMap;
    }

    public Map<String, String> assembleParameters() {
        if (this.where.keySet().size() > 0) {
            this.parameters.put("where", AVUtils.restfulServerData(compileWhereOperationMap()));
        }
        if (this.limit > 0) {
            this.parameters.put("limit", Integer.toString(this.limit));
        }
        if (this.skip > 0) {
            this.parameters.put("skip", Integer.toString(this.skip));
        }
        if (!AVUtils.isBlankString(this.order)) {
            this.parameters.put("order", this.order);
        }
        if (!AVUtils.isEmptyList(this.include)) {
            this.parameters.put("include", AVUtils.joinCollection(this.include, ","));
        }
        if (this.selectedKeys != null && this.selectedKeys.size() > 0) {
            this.parameters.put("keys", AVUtils.joinCollection(this.selectedKeys, ","));
        }
        return this.parameters;
    }

    public void addWhereItem(QueryOperation queryOperation) {
        List list = (List) this.where.get(queryOperation.key);
        if (list == null) {
            list = new LinkedList();
            this.where.put(queryOperation.key, list);
        }
        removeDuplications(queryOperation, list);
        list.add(queryOperation);
    }

    public void addWhereItem(String str, String str2, Object obj) {
        addWhereItem(new QueryOperation(str, str2, obj));
    }

    private void removeDuplications(QueryOperation queryOperation, List<QueryOperation> list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((QueryOperation) it.next()).sameOp(queryOperation)) {
                it.remove();
            }
        }
    }

    public void addOrItems(QueryOperation queryOperation) {
        List list;
        List list2 = (List) this.where.get(QueryOperation.OR_OP);
        if (list2 == null) {
            LinkedList linkedList = new LinkedList();
            this.where.put(QueryOperation.OR_OP, linkedList);
            list = linkedList;
        } else {
            list = list2;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((QueryOperation) it.next()).equals(queryOperation)) {
                it.remove();
            }
        }
        list.add(queryOperation);
    }

    public void whereWithinRadians(String str, AVGeoPoint aVGeoPoint, double d) {
        Map createMap = AVUtils.createMap("$nearSphere", AVUtils.mapFromGeoPoint(aVGeoPoint));
        createMap.put("maxDistanceInRadians", Double.valueOf(d));
        addWhereItem(new QueryOperation(str, null, createMap));
    }

    public void whereGreaterThanOrEqualTo(String str, Object obj) {
        addWhereItem(new QueryOperation(str, "$gte", obj));
    }

    public void whereContainedIn(String str, Collection<? extends Object> collection) {
        addWhereItem(str, "$in", collection);
    }

    public void whereExists(String str) {
        addWhereItem(str, "$exists", Boolean.valueOf(true));
    }

    public void whereGreaterThan(String str, Object obj) {
        addWhereItem(str, "$gt", obj);
    }

    public void whereLessThan(String str, Object obj) {
        addWhereItem(str, "$lt", obj);
    }

    public void whereLessThanOrEqualTo(String str, Object obj) {
        addWhereItem(str, "$lte", obj);
    }

    public void whereMatches(String str, String str2) {
        addWhereItem(str, "$regex", str2);
    }

    public void whereMatches(String str, String str2, String str3) {
        addWhereItem(str, "$regex", str2);
        addWhereItem(str, "$options", str3);
    }

    public void whereNear(String str, AVGeoPoint aVGeoPoint) {
        addWhereItem(str, "$nearSphere", AVUtils.mapFromGeoPoint(aVGeoPoint));
    }

    public void whereNotContainedIn(String str, Collection<? extends Object> collection) {
        addWhereItem(str, "$nin", collection);
    }

    public void whereNotEqualTo(String str, Object obj) {
        addWhereItem(str, "$ne", obj);
    }

    public void whereEqualTo(String str, Object obj) {
        if (obj instanceof AVObject) {
            addWhereItem(str, QueryOperation.EQUAL_OP, AVUtils.mapFromPointerObject((AVObject) obj));
        } else {
            addWhereItem(str, QueryOperation.EQUAL_OP, obj);
        }
    }

    public void whereStartsWith(String str, String str2) {
        whereMatches(str, String.format("^%s.*", new Object[]{str2}));
    }

    public void whereWithinGeoBox(String str, AVGeoPoint aVGeoPoint, AVGeoPoint aVGeoPoint2) {
        List linkedList = new LinkedList();
        linkedList.add(AVUtils.mapFromGeoPoint(aVGeoPoint));
        linkedList.add(AVUtils.mapFromGeoPoint(aVGeoPoint2));
        addWhereItem(str, "$within", AVUtils.createMap("$box", linkedList));
    }

    public void whereWithinKilometers(String str, AVGeoPoint aVGeoPoint, double d) {
        Map createMap = AVUtils.createMap("$nearSphere", AVUtils.mapFromGeoPoint(aVGeoPoint));
        createMap.put("$maxDistanceInKilometers", Double.valueOf(d));
        addWhereItem(str, null, createMap);
    }

    public void whereWithinMiles(String str, AVGeoPoint aVGeoPoint, double d) {
        Map createMap = AVUtils.createMap("$nearSphere", AVUtils.mapFromGeoPoint(aVGeoPoint));
        createMap.put("$maxDistanceInMiles", Double.valueOf(d));
        addWhereItem(str, null, createMap);
    }

    public void whereEndsWith(String str, String str2) {
        whereMatches(str, String.format(".*%s$", new Object[]{str2}));
    }

    public void whereContains(String str, String str2) {
        whereMatches(str, String.format(".*%s.*", new Object[]{str2}));
    }

    public void whereSizeEqual(String str, int i) {
        addWhereItem(str, "$size", Integer.valueOf(i));
    }

    public void whereContainsAll(String str, Collection<?> collection) {
        addWhereItem(str, "$all", collection);
    }

    public void whereDoesNotExist(String str) {
        addWhereItem(str, "$exists", Boolean.valueOf(false));
    }
}
