package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import io.rong.common.ParcelUtils;
import io.rong.imlib.model.PublicServiceMenu.PublicServiceMenuItemType;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PublicServiceMenuItem implements Parcelable {
    public static final Creator<PublicServiceMenuItem> CREATOR = new C53791();
    private String id;
    private String name;
    private ArrayList<PublicServiceMenuItem> subMenuItems = new ArrayList();
    private PublicServiceMenuItemType type;
    private String url;

    /* renamed from: io.rong.imlib.model.PublicServiceMenuItem$1 */
    static class C53791 implements Creator<PublicServiceMenuItem> {
        C53791() {
        }

        public PublicServiceMenuItem createFromParcel(Parcel parcel) {
            return new PublicServiceMenuItem(parcel);
        }

        public PublicServiceMenuItem[] newArray(int i) {
            return new PublicServiceMenuItem[i];
        }
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public PublicServiceMenuItemType getType() {
        return this.type;
    }

    public ArrayList<PublicServiceMenuItem> getSubMenuItems() {
        return this.subMenuItems;
    }

    public String getId() {
        return this.id;
    }

    public PublicServiceMenuItem(JSONObject jSONObject) throws Exception {
        try {
            if (jSONObject.has("id")) {
                this.id = jSONObject.optString("id");
            }
            if (jSONObject.has("name")) {
                this.name = jSONObject.optString("name");
            }
            if (jSONObject.has("url")) {
                this.url = jSONObject.optString("url");
            }
            if (jSONObject.has("type")) {
                this.type = PublicServiceMenuItemType.setValue(jSONObject.optInt("type"));
                if (this.type != null && this.type == PublicServiceMenuItemType.Group && jSONObject.has("children")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("children");
                    if (jSONArray != null) {
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject optJSONObject = jSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                try {
                                    this.subMenuItems.add(new PublicServiceMenuItem(optJSONObject));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            throw new Exception("PublicServiceMenuItem parse error!");
        }
    }

    private PublicServiceMenuItem() {
    }

    public int describeContents() {
        return 0;
    }

    public PublicServiceMenuItem(Parcel parcel) {
        this.id = ParcelUtils.readFromParcel(parcel);
        this.name = ParcelUtils.readFromParcel(parcel);
        this.url = ParcelUtils.readFromParcel(parcel);
        this.type = PublicServiceMenuItemType.setValue(ParcelUtils.readIntFromParcel(parcel).intValue());
        this.subMenuItems = ParcelUtils.readListFromParcel(parcel, PublicServiceMenuItem.class);
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.id);
        ParcelUtils.writeToParcel(parcel, this.name);
        ParcelUtils.writeToParcel(parcel, this.url);
        ParcelUtils.writeToParcel(parcel, this.type != null ? Integer.valueOf(this.type.getValue()) : null);
        ParcelUtils.writeToParcel(parcel, this.subMenuItems);
    }
}
