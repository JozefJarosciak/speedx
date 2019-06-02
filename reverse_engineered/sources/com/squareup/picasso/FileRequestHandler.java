package com.squareup.picasso;

import android.content.Context;
import android.media.ExifInterface;
import android.net.Uri;
import ch.qos.logback.core.joran.action.Action;
import com.alibaba.fastjson.asm.Opcodes;
import com.squareup.picasso.RequestHandler.Result;
import java.io.IOException;

class FileRequestHandler extends ContentStreamRequestHandler {
    FileRequestHandler(Context context) {
        super(context);
    }

    public boolean canHandleRequest(Request request) {
        return Action.FILE_ATTRIBUTE.equals(request.uri.getScheme());
    }

    public Result load(Request request, int i) throws IOException {
        return new Result(null, getInputStream(request), Picasso$LoadedFrom.DISK, getFileExifRotation(request.uri));
    }

    static int getFileExifRotation(Uri uri) throws IOException {
        switch (new ExifInterface(uri.getPath()).getAttributeInt("Orientation", 1)) {
            case 3:
                return Opcodes.GETFIELD;
            case 6:
                return 90;
            case 8:
                return 270;
            default:
                return 0;
        }
    }
}
