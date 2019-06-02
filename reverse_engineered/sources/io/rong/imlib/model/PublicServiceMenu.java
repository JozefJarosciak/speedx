package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import io.rong.common.ParcelUtils;
import java.util.ArrayList;
import org.json.JSONArray;

public class PublicServiceMenu implements Parcelable {
    public static final Creator<PublicServiceMenu> CREATOR = new C53781();
    private ArrayList<PublicServiceMenuItem> menuItems;

    /* renamed from: io.rong.imlib.model.PublicServiceMenu$1 */
    static class C53781 implements Creator<PublicServiceMenu> {
        C53781() {
        }

        public PublicServiceMenu createFromParcel(Parcel parcel) {
            return new PublicServiceMenu(parcel);
        }

        public PublicServiceMenu[] newArray(int i) {
            return new PublicServiceMenu[i];
        }
    }

    public enum PublicServiceMenuItemType {
        Group(0, "GROUP"),
        View(1, "VIEW"),
        Click(2, "CLICK"),
        Entry(3, "ENTRY");
        
        private String command;
        private int value;

        private PublicServiceMenuItemType(int i, String str) {
            this.value = 1;
            this.value = i;
            this.command = str;
        }

        public int getValue() {
            return this.value;
        }

        public String getMessage() {
            return this.command;
        }

        public static PublicServiceMenuItemType setValue(int i) {
            for (PublicServiceMenuItemType publicServiceMenuItemType : values()) {
                if (i == publicServiceMenuItemType.getValue()) {
                    return publicServiceMenuItemType;
                }
            }
            return null;
        }
    }

    public ArrayList<PublicServiceMenuItem> getMenuItems() {
        return this.menuItems;
    }

    public void setMenuItems(ArrayList<PublicServiceMenuItem> arrayList) {
        this.menuItems = arrayList;
    }

    public PublicServiceMenu(JSONArray jSONArray) {
        this.menuItems = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                this.menuItems.add(new PublicServiceMenuItem(jSONArray.optJSONObject(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private PublicServiceMenu() {
    }

    public int describeContents() {
        return 0;
    }

    public PublicServiceMenu(Parcel parcel) {
        this.menuItems = ParcelUtils.readListFromParcel(parcel, PublicServiceMenuItem.class);
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeListToParcel(parcel, this.menuItems);
    }
}
