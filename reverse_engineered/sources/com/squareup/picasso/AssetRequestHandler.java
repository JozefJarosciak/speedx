package com.squareup.picasso;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import ch.qos.logback.core.joran.action.Action;
import com.squareup.picasso.RequestHandler.Result;
import java.io.IOException;

class AssetRequestHandler extends RequestHandler {
    protected static final String ANDROID_ASSET = "android_asset";
    private static final int ASSET_PREFIX_LENGTH = "file:///android_asset/".length();
    private final AssetManager assetManager;

    public AssetRequestHandler(Context context) {
        this.assetManager = context.getAssets();
    }

    public boolean canHandleRequest(Request request) {
        Uri uri = request.uri;
        if (Action.FILE_ATTRIBUTE.equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && ANDROID_ASSET.equals(uri.getPathSegments().get(0))) {
            return true;
        }
        return false;
    }

    public Result load(Request request, int i) throws IOException {
        return new Result(this.assetManager.open(getFilePath(request)), Picasso$LoadedFrom.DISK);
    }

    static String getFilePath(Request request) {
        return request.uri.toString().substring(ASSET_PREFIX_LENGTH);
    }
}
