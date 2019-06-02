package com.beastbikes.android.modules.train.dto;

import ch.qos.logback.core.CoreConstants;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class TrainCourseDTO extends CourseDTO implements Serializable {
    private ArrayList<Program> programs;

    public class Program implements Serializable {
        /* renamed from: a */
        final /* synthetic */ TrainCourseDTO f11233a;
        private String enName;
        private String name;
        private int programTime;
        private int recycleCount;
        private ArrayList<Stage> stages;

        public Program(TrainCourseDTO trainCourseDTO, JSONObject jSONObject) {
            this.f11233a = trainCourseDTO;
            if (jSONObject != null) {
                this.name = jSONObject.optString("name");
                this.enName = jSONObject.optString("en_name");
                this.recycleCount = jSONObject.optInt("cycle_count");
                JSONArray optJSONArray = jSONObject.optJSONArray("stages");
                if (optJSONArray != null) {
                    int length = optJSONArray.length();
                    this.stages = new ArrayList();
                    for (int i = 0; i < length; i++) {
                        this.stages.add(new Stage(trainCourseDTO, optJSONArray.optJSONObject(i)));
                    }
                }
                this.programTime = jSONObject.optInt("program_time");
            }
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getEnName() {
            return this.enName;
        }

        public void setEnName(String str) {
            this.enName = str;
        }

        public int getRecycleCount() {
            return this.recycleCount;
        }

        public void setRecycleCount(int i) {
            this.recycleCount = i;
        }

        public ArrayList<Stage> getStages() {
            return this.stages;
        }

        public void setStages(ArrayList<Stage> arrayList) {
            this.stages = arrayList;
        }

        public int getProgramTime() {
            return this.programTime;
        }

        public void setProgramTime(int i) {
            this.programTime = i;
        }

        public String toString() {
            return "Program{name='" + this.name + CoreConstants.SINGLE_QUOTE_CHAR + ", enName='" + this.enName + CoreConstants.SINGLE_QUOTE_CHAR + ", recycleCount=" + this.recycleCount + ", stages=" + this.stages + ", programTime=" + this.programTime + CoreConstants.CURLY_RIGHT;
        }
    }

    public class Stage implements Serializable {
        /* renamed from: a */
        final /* synthetic */ TrainCourseDTO f11234a;
        private int cadenceHigh;
        private int cadenceLow;
        private int powerHigh;
        private int powerLow;
        private int stageTime;

        public Stage(TrainCourseDTO trainCourseDTO, JSONObject jSONObject) {
            this.f11234a = trainCourseDTO;
            this.powerHigh = jSONObject.optInt("power_high");
            this.powerLow = jSONObject.optInt("power_low");
            this.cadenceHigh = jSONObject.optInt("cadence_high");
            this.cadenceLow = jSONObject.optInt("cadence_low");
            this.stageTime = jSONObject.optInt("stage_time");
        }

        public int getPowerHigh() {
            return this.powerHigh;
        }

        public void setPowerHigh(int i) {
            this.powerHigh = i;
        }

        public int getPowerLow() {
            return this.powerLow;
        }

        public void setPowerLow(int i) {
            this.powerLow = i;
        }

        public int getCadenceHigh() {
            return this.cadenceHigh;
        }

        public void setCadenceHigh(int i) {
            this.cadenceHigh = i;
        }

        public int getCadenceLow() {
            return this.cadenceLow;
        }

        public void setCadenceLow(int i) {
            this.cadenceLow = i;
        }

        public int getStageTime() {
            return this.stageTime;
        }

        public void setStageTime(int i) {
            this.stageTime = i;
        }
    }

    public TrainCourseDTO(JSONObject jSONObject) {
        super(jSONObject);
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("programs");
            if (optJSONArray != null) {
                this.programs = new ArrayList();
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    this.programs.add(new Program(this, optJSONArray.optJSONObject(i)));
                }
            }
        }
    }

    public ArrayList<Program> getPrograms() {
        return this.programs;
    }

    public void setPrograms(ArrayList<Program> arrayList) {
        this.programs = arrayList;
    }

    public String toJSON() {
        return new Gson().toJson((Object) this);
    }
}
