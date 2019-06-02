package com.beastbikes.android.modules.cycling.grid.p066a;

import android.app.Activity;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.modules.cycling.grid.dao.C2171a;
import com.beastbikes.android.modules.cycling.grid.dao.entity.Grid;
import com.beastbikes.android.modules.cycling.grid.dto.GridDTO;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: GridManager */
/* renamed from: com.beastbikes.android.modules.cycling.grid.a.a */
public class C2170a extends C1397a {
    /* renamed from: a */
    private static final Logger f10159a = LoggerFactory.getLogger(C2170a.class);
    /* renamed from: b */
    private Activity f10160b;
    /* renamed from: c */
    private C2171a f10161c = new C2171a(((BeastBikes) BeastBikes.j().getApplicationContext()).d());
    /* renamed from: d */
    private C1414b f10162d;

    public C2170a(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        this.f10160b = activity;
        this.f10162d = (C1414b) new C1772d(activity).m9399a(C1414b.class, C1768c.f8075a, C1768c.m9391a(activity));
    }

    /* renamed from: a */
    public List<GridDTO> m11129a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject a = this.f10162d.a(str);
            if (a == null) {
                return m11130b(str);
            }
            if (a.optInt("code") == 0) {
                JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                List<GridDTO> arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    GridDTO gridDTO = new GridDTO(optJSONArray.optJSONObject(i), str);
                    arrayList.add(gridDTO);
                    Grid grid = new Grid();
                    grid.setId(String.valueOf(gridDTO.getGridId()));
                    grid.setCount(gridDTO.getCount());
                    grid.setUserId(gridDTO.getUserId());
                    grid.setUnlockAt(gridDTO.getUnlockAt());
                    arrayList2.add(grid);
                }
                if (!(arrayList2 == null || arrayList2.isEmpty())) {
                    try {
                        this.f10161c.m9026b(arrayList2);
                    } catch (Exception e) {
                        f10159a.error("Create or update grids is error");
                    }
                }
                return arrayList;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f10160b, optString);
            }
            return m11130b(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public List<GridDTO> m11130b(String str) {
        List<Grid> a = this.f10161c.m11131a(str);
        if (a == null || a.isEmpty()) {
            return null;
        }
        List<GridDTO> arrayList = new ArrayList();
        for (Grid gridDTO : a) {
            arrayList.add(new GridDTO(gridDTO));
        }
        return arrayList;
    }
}
