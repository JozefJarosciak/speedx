package com.mapbox.services.android.geocoder.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.TextView;
import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.commons.utils.TextUtils;
import com.mapbox.services.geocoding.v5.MapboxGeocoding.Builder;
import com.mapbox.services.geocoding.v5.models.CarmenFeature;
import com.mapbox.services.geocoding.v5.models.GeocodingResponse;
import java.io.IOException;
import java.util.List;
import retrofit2.Response;

public class GeocoderAdapter extends BaseAdapter implements Filterable {
    private String accessToken;
    private double[] bbox;
    private final Context context;
    private String country;
    private List<CarmenFeature> features;
    private GeocoderFilter geocoderFilter;
    private Position position;
    private String type;

    private class GeocoderFilter extends Filter {
        private final Builder builder = new Builder();

        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            if (TextUtils.isEmpty(charSequence)) {
                return filterResults;
            }
            try {
                this.builder.setAccessToken(GeocoderAdapter.this.getAccessToken()).setLocation(charSequence.toString()).setAutocomplete(true);
                if (GeocoderAdapter.this.getCountry() != null) {
                    this.builder.setCountry(GeocoderAdapter.this.getCountry());
                }
                if (GeocoderAdapter.this.getProximity() != null) {
                    this.builder.setProximity(GeocoderAdapter.this.getProximity());
                }
                if (GeocoderAdapter.this.getType() != null) {
                    this.builder.setGeocodingType(GeocoderAdapter.this.getType());
                }
                if (GeocoderAdapter.this.getBbox() != null) {
                    this.builder.setBbox(GeocoderAdapter.this.bbox[0], GeocoderAdapter.this.bbox[1], GeocoderAdapter.this.bbox[2], GeocoderAdapter.this.bbox[3]);
                }
                Response executeCall = this.builder.build().executeCall();
                if (executeCall == null) {
                    return filterResults;
                }
                GeocoderAdapter.this.features = ((GeocodingResponse) executeCall.body()).getFeatures();
                filterResults.values = GeocoderAdapter.this.features;
                filterResults.count = GeocoderAdapter.this.features.size();
                return filterResults;
            } catch (IOException e) {
                e.printStackTrace();
                return filterResults;
            } catch (ServicesException e2) {
                e2.printStackTrace();
                return filterResults;
            }
        }

        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (filterResults == null || filterResults.count <= 0) {
                GeocoderAdapter.this.notifyDataSetInvalidated();
                return;
            }
            GeocoderAdapter.this.features = (List) filterResults.values;
            GeocoderAdapter.this.notifyDataSetChanged();
        }
    }

    public GeocoderAdapter(Context context) {
        this.context = context;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public double[] getBbox() {
        return this.bbox;
    }

    public void setBbox(Position position, Position position2) {
        setBbox(position2.getLongitude(), position2.getLatitude(), position.getLongitude(), position.getLatitude());
    }

    public void setBbox(double d, double d2, double d3, double d4) {
        if (this.bbox == null) {
            this.bbox = new double[4];
        }
        this.bbox[0] = d;
        this.bbox[1] = d2;
        this.bbox[2] = d3;
        this.bbox[3] = d4;
    }

    public Position getProximity() {
        return this.position;
    }

    public void setProximity(Position position) {
        this.position = position;
    }

    public int getCount() {
        return this.features.size();
    }

    public CarmenFeature getItem(int i) {
        return (CarmenFeature) this.features.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate;
        if (view == null) {
            inflate = LayoutInflater.from(this.context).inflate(17367050, viewGroup, false);
        } else {
            inflate = view;
        }
        ((TextView) inflate).setText(getItem(i).toString());
        return inflate;
    }

    public Filter getFilter() {
        if (this.geocoderFilter == null) {
            this.geocoderFilter = new GeocoderFilter();
        }
        return this.geocoderFilter;
    }
}
