package io.rong.imkit.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;
import io.rong.common.FileUtils;
import io.rong.imkit.C4974R;
import java.io.File;

public class PicturePopupWindow extends PopupWindow {
    private Button btn_cancel;
    private Button btn_save_pic;

    /* renamed from: io.rong.imkit.widget.PicturePopupWindow$1 */
    class C51531 implements OnClickListener {
        C51531() {
        }

        public void onClick(View view) {
            PicturePopupWindow.this.dismiss();
        }
    }

    /* renamed from: io.rong.imkit.widget.PicturePopupWindow$3 */
    class C51553 implements OnClickListener {
        C51553() {
        }

        public void onClick(View view) {
            PicturePopupWindow.this.dismiss();
        }
    }

    public PicturePopupWindow(final Context context, final File file) {
        super(context);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C4974R.layout.rc_pic_popup_window, null);
        inflate.setOnClickListener(new C51531());
        this.btn_save_pic = (Button) inflate.findViewById(C4974R.id.rc_content);
        this.btn_save_pic.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                File file = new File(Environment.getExternalStorageDirectory(), context.getString(C4974R.string.rc_image_default_saved_path));
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (file == null || !file.exists()) {
                    Toast.makeText(context, context.getString(C4974R.string.rc_src_file_not_found), 0).show();
                } else {
                    FileUtils.copyFile(file, file.getPath() + File.separator, System.currentTimeMillis() + ".jpg");
                    MediaScannerConnection.scanFile(context, new String[]{file.getPath() + File.separator + r0}, null, null);
                    Toast.makeText(context, String.format(context.getString(C4974R.string.rc_save_picture_at), new Object[]{file.getPath() + File.separator + r0}), 0).show();
                }
                PicturePopupWindow.this.dismiss();
            }
        });
        this.btn_cancel = (Button) inflate.findViewById(C4974R.id.rc_btn_cancel);
        this.btn_cancel.setOnClickListener(new C51553());
        setContentView(inflate);
        setWidth(-1);
        setHeight(-2);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(-1342177280));
    }
}
