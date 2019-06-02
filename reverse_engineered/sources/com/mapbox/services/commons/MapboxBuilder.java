package com.mapbox.services.commons;

import com.mapbox.services.commons.utils.MapboxUtils;

public abstract class MapboxBuilder {
    public abstract Object build() throws ServicesException;

    public abstract String getAccessToken();

    public abstract MapboxBuilder setAccessToken(String str);

    protected void validateAccessToken(String str) throws ServicesException {
        if (!MapboxUtils.isAccessTokenValid(str)) {
            throw new ServicesException("Using Mapbox Services requires setting a valid access token.");
        }
    }
}
