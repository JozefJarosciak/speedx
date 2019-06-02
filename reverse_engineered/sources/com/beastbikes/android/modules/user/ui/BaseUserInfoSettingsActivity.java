package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import com.avos.avoscloud.AVAnalytics;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.preferences.ui.CutAvatarActivity;
import com.beastbikes.android.modules.user.dto.SearchRegionDTO;
import com.beastbikes.android.modules.user.p152b.C2407e;
import com.beastbikes.android.modules.user.view.C2529f;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1457a(a = "登录/注册后个人资料设置与 设置 - 个人信息 - 个人资料设置页面父类")
public abstract class BaseUserInfoSettingsActivity extends SessionFragmentActivity implements OnClickListener, C2529f {
    /* renamed from: d */
    private static final Logger f6297d = LoggerFactory.getLogger("BaseUserInfoSettingsActivity");
    /* renamed from: a */
    protected String f6298a = "";
    /* renamed from: b */
    protected C2407e f6299b;
    /* renamed from: c */
    protected SearchRegionDTO f6300c;
    /* renamed from: e */
    private File f6301e = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), mo2809i());
    /* renamed from: f */
    private C1802i f6302f;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() == null) {
            finish();
        } else {
            this.f6299b = new C2407e(this);
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        contextMenu.add(0, C1373R.id.menu_album, 0, C1373R.string.str_album);
        contextMenu.add(0, C1373R.id.menu_camera, 0, C1373R.string.str_camera);
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_album:
                intent = new Intent("android.intent.action.PICK", null);
                intent.setDataAndType(Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 2);
                f6297d.info("select image from gallery");
                return true;
            case C1373R.id.menu_camera:
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra("output", Uri.fromFile(this.f6301e));
                startActivityForResult(intent, 3);
                f6297d.info("select image from camera");
                return true;
            default:
                return super.onContextItemSelected(menuItem);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_with_one_title_without_icon:
                this.f6299b.a();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onClick(View view) {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Cursor query;
        String string;
        Intent intent2;
        Throwable e;
        Cursor cursor;
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 2:
                if (i2 == -1 && intent != null) {
                    Uri data = intent.getData();
                    String[] strArr = new String[]{"_data"};
                    try {
                        query = getContentResolver().query(data, strArr, null, null, null);
                        if (query != null) {
                            try {
                                if (query.moveToFirst()) {
                                    string = query.getString(query.getColumnIndex(strArr[0]));
                                    if (!(query == null || query.isClosed())) {
                                        query.close();
                                    }
                                    if (TextUtils.isEmpty(string)) {
                                        string = data.getPath();
                                    }
                                    intent2 = new Intent(this, CutAvatarActivity.class);
                                    intent2.putExtra("path", string);
                                    startActivityForResult(intent2, 4);
                                    return;
                                }
                            } catch (Exception e2) {
                                e = e2;
                                cursor = query;
                                try {
                                    AVAnalytics.onError(this, Log.getStackTraceString(e));
                                    f6297d.error("Get photo path from gallery failed", e);
                                    cursor.close();
                                    string = TextUtils.isEmpty(null) ? null : data.getPath();
                                    intent2 = new Intent(this, CutAvatarActivity.class);
                                    intent2.putExtra("path", string);
                                    startActivityForResult(intent2, 4);
                                    return;
                                } catch (Throwable th) {
                                    e = th;
                                    query = cursor;
                                    query.close();
                                    if (TextUtils.isEmpty(null)) {
                                        data.getPath();
                                    }
                                    throw e;
                                }
                            } catch (Throwable th2) {
                                e = th2;
                                query.close();
                                if (TextUtils.isEmpty(null)) {
                                    data.getPath();
                                }
                                throw e;
                            }
                        }
                        AVAnalytics.onError(this, "get photo path failed:" + data.toString());
                        string = null;
                        query.close();
                        if (TextUtils.isEmpty(string)) {
                            string = data.getPath();
                        }
                        break;
                    } catch (Exception e3) {
                        e = e3;
                        cursor = null;
                        AVAnalytics.onError(this, Log.getStackTraceString(e));
                        f6297d.error("Get photo path from gallery failed", e);
                        if (!(cursor == null || cursor.isClosed())) {
                            cursor.close();
                        }
                        if (TextUtils.isEmpty(null)) {
                        }
                        intent2 = new Intent(this, CutAvatarActivity.class);
                        intent2.putExtra("path", string);
                        startActivityForResult(intent2, 4);
                        return;
                    } catch (Throwable th3) {
                        e = th3;
                        query = null;
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        if (TextUtils.isEmpty(null)) {
                            data.getPath();
                        }
                        throw e;
                    }
                    intent2 = new Intent(this, CutAvatarActivity.class);
                    intent2.putExtra("path", string);
                    startActivityForResult(intent2, 4);
                    return;
                }
                return;
            case 3:
                if (i2 == -1) {
                    Intent intent3 = new Intent(this, CutAvatarActivity.class);
                    intent3.putExtra("path", this.f6301e.getAbsolutePath());
                    startActivityForResult(intent3, 4);
                    return;
                }
                return;
            case 4:
                if (i2 == 4) {
                    string = intent.getStringExtra("path");
                    this.f6298a = string;
                    mo2805a(string);
                    return;
                }
                return;
            case 5:
                if (intent != null) {
                    SearchRegionDTO searchRegionDTO = (SearchRegionDTO) intent.getSerializableExtra("key_region_dto");
                    if (searchRegionDTO != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        if (!TextUtils.isEmpty(searchRegionDTO.getProvince())) {
                            stringBuilder.append(searchRegionDTO.getProvince());
                        }
                        if (!TextUtils.isEmpty(searchRegionDTO.getCity())) {
                            stringBuilder.append(", ");
                            stringBuilder.append(searchRegionDTO.getCity());
                        }
                        this.f6300c = searchRegionDTO;
                        mo2806b(stringBuilder.toString());
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: i */
    private String mo2809i() {
        return new SimpleDateFormat("'BEAST'_yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".jpg";
    }

    /* renamed from: a */
    public BaseUserInfoSettingsActivity mo2808a() {
        return (BaseUserInfoSettingsActivity) new WeakReference(this).get();
    }

    /* renamed from: b */
    public String m7488b() {
        return this.f6298a;
    }

    /* renamed from: c */
    public String mo2804c() {
        return null;
    }

    /* renamed from: e */
    public SearchRegionDTO m7491e() {
        return this.f6300c;
    }

    /* renamed from: f */
    public void m7492f() {
        f6297d.info("onUpdateFailed");
    }

    /* renamed from: g */
    public void m7493g() {
        if (this.f6302f == null) {
            this.f6302f = new C1802i(this, C1373R.string.str_loading, true);
        }
        if (!this.f6302f.isShowing()) {
            this.f6302f.show();
        }
    }

    /* renamed from: h */
    public void m7494h() {
        if (this.f6302f != null && this.f6302f.isShowing()) {
            this.f6302f.dismiss();
        }
    }

    /* renamed from: a */
    public void mo2805a(String str) {
    }

    /* renamed from: b */
    public void mo2806b(String str) {
    }
}
