package org.xclcharts.chart;

import android.support.v4.view.ViewCompat;
import java.util.LinkedList;
import java.util.List;

public class BarData {
    private Integer mColor;
    private List<Integer> mDataColor;
    private List<Double> mDataSet;
    private String mKey;

    public BarData(String str, List<Double> list, Integer num) {
        setKey(str);
        setColor(num);
        setDataSet(list);
    }

    public BarData(String str, Double d) {
        setKey(str);
        List linkedList = new LinkedList();
        linkedList.add(d);
        setDataSet(linkedList);
        setColor(Integer.valueOf(ViewCompat.MEASURED_STATE_MASK));
    }

    public BarData(String str, List<Double> list, List<Integer> list2, Integer num) {
        setKey(str);
        setColor(num);
        setDataSet(list);
        setDataColor(list2);
    }

    public void setDataColor(List<Integer> list) {
        if (this.mDataColor != null) {
            this.mDataColor.clear();
        }
        this.mDataColor = list;
    }

    public List<Integer> getDataColor() {
        return this.mDataColor;
    }

    public void setDataSet(List<Double> list) {
        if (this.mDataSet != null) {
            this.mDataSet.clear();
        }
        this.mDataSet = list;
    }

    public void setKey(String str) {
        this.mKey = str;
    }

    public void setColor(Integer num) {
        this.mColor = num;
    }

    public List<Double> getDataSet() {
        return this.mDataSet;
    }

    public String getKey() {
        return this.mKey;
    }

    public Integer getColor() {
        return this.mColor;
    }
}
