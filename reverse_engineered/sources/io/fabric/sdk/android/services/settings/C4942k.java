package io.fabric.sdk.android.services.settings;

import com.avos.avoscloud.AVStatus;
import com.beastbikes.framework.ui.android.WebActivity;
import com.mapbox.mapboxsdk.style.layers.Property;
import io.fabric.sdk.android.services.common.C4878j;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DefaultSettingsJsonTransform */
/* renamed from: io.fabric.sdk.android.services.settings.k */
class C4942k implements C4941u {
    C4942k() {
    }

    /* renamed from: a */
    public C4952s mo6266a(C4878j c4878j, JSONObject jSONObject) throws JSONException {
        int optInt = jSONObject.optInt("settings_version", 0);
        int optInt2 = jSONObject.optInt("cache_duration", 3600);
        return new C4952s(m19392a(c4878j, (long) optInt2, jSONObject), m19393a(jSONObject.getJSONObject("app")), m19397e(jSONObject.getJSONObject("session")), m19398f(jSONObject.getJSONObject("prompt")), m19395c(jSONObject.getJSONObject("features")), m19396d(jSONObject.getJSONObject("analytics")), m19399g(jSONObject.getJSONObject("beta")), optInt, optInt2);
    }

    /* renamed from: a */
    private C4934e m19393a(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("identifier");
        String string2 = jSONObject.getString("status");
        String string3 = jSONObject.getString("url");
        String string4 = jSONObject.getString("reports_url");
        boolean optBoolean = jSONObject.optBoolean("update_required", false);
        C4932c c4932c = null;
        if (jSONObject.has("icon") && jSONObject.getJSONObject("icon").has("hash")) {
            c4932c = m19394b(jSONObject.getJSONObject("icon"));
        }
        return new C4934e(string, string2, string3, string4, optBoolean, c4932c);
    }

    /* renamed from: b */
    private C4932c m19394b(JSONObject jSONObject) throws JSONException {
        return new C4932c(jSONObject.getString("hash"), jSONObject.getInt(Property.ICON_TEXT_FIT_WIDTH), jSONObject.getInt(Property.ICON_TEXT_FIT_HEIGHT));
    }

    /* renamed from: c */
    private C4945m m19395c(JSONObject jSONObject) {
        return new C4945m(jSONObject.optBoolean("prompt_enabled", false), jSONObject.optBoolean("collect_logged_exceptions", true), jSONObject.optBoolean("collect_reports", true), jSONObject.optBoolean("collect_analytics", false));
    }

    /* renamed from: d */
    private C4931b m19396d(JSONObject jSONObject) {
        return new C4931b(jSONObject.optString("url", "https://e.crashlytics.com/spi/v2/events"), jSONObject.optInt("flush_interval_secs", 600), jSONObject.optInt("max_byte_size_per_file", 8000), jSONObject.optInt("max_file_count_per_send", 1), jSONObject.optInt("max_pending_send_file_count", 100), jSONObject.optBoolean("track_custom_events", true), jSONObject.optBoolean("track_predefined_events", true), jSONObject.optInt("sampling_rate", 1), jSONObject.optBoolean("flush_on_background", true));
    }

    /* renamed from: e */
    private C4948p m19397e(JSONObject jSONObject) throws JSONException {
        return new C4948p(jSONObject.optInt("log_buffer_size", 64000), jSONObject.optInt("max_chained_exception_depth", 8), jSONObject.optInt("max_custom_exception_events", 64), jSONObject.optInt("max_custom_key_value_pairs", 64), jSONObject.optInt("identifier_mask", 255), jSONObject.optBoolean("send_session_without_crash", false));
    }

    /* renamed from: f */
    private C4947o m19398f(JSONObject jSONObject) throws JSONException {
        return new C4947o(jSONObject.optString(WebActivity.EXTRA_TITLE, "Send Crash Report?"), jSONObject.optString(AVStatus.MESSAGE_TAG, "Looks like we crashed! Please help us fix the problem by sending a crash report."), jSONObject.optString("send_button_title", "Send"), jSONObject.optBoolean("show_cancel_button", true), jSONObject.optString("cancel_button_title", "Don't Send"), jSONObject.optBoolean("show_always_send_button", true), jSONObject.optString("always_send_button_title", "Always Send"));
    }

    /* renamed from: g */
    private C4935f m19399g(JSONObject jSONObject) throws JSONException {
        return new C4935f(jSONObject.optString("update_endpoint", C4953t.f17350a), jSONObject.optInt("update_suspend_duration", 3600));
    }

    /* renamed from: a */
    private long m19392a(C4878j c4878j, long j, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("expires_at")) {
            return jSONObject.getLong("expires_at");
        }
        return c4878j.mo6251a() + (1000 * j);
    }
}
