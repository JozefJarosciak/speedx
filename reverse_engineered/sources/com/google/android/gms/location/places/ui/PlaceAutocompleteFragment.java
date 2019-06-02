package com.google.android.gms.location.places.ui;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.gms.R$id;
import com.google.android.gms.R$layout;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete.IntentBuilder;
import com.google.android.gms.maps.model.LatLngBounds;

@TargetApi(12)
public class PlaceAutocompleteFragment extends Fragment {
    private View agm;
    private View agn;
    private EditText ago;
    @Nullable
    private LatLngBounds agp;
    @Nullable
    private AutocompleteFilter agq;
    @Nullable
    private PlaceSelectionListener agr;

    /* renamed from: com.google.android.gms.location.places.ui.PlaceAutocompleteFragment$1 */
    class C34481 implements OnClickListener {
        final /* synthetic */ PlaceAutocompleteFragment ags;

        C34481(PlaceAutocompleteFragment placeAutocompleteFragment) {
            this.ags = placeAutocompleteFragment;
        }

        public void onClick(View view) {
            this.ags.zzbpf();
        }
    }

    /* renamed from: com.google.android.gms.location.places.ui.PlaceAutocompleteFragment$2 */
    class C34492 implements OnClickListener {
        final /* synthetic */ PlaceAutocompleteFragment ags;

        C34492(PlaceAutocompleteFragment placeAutocompleteFragment) {
            this.ags = placeAutocompleteFragment;
        }

        public void onClick(View view) {
            this.ags.setText("");
        }
    }

    private void zzbpe() {
        int i = 0;
        int i2 = !this.ago.getText().toString().isEmpty() ? 1 : 0;
        View view = this.agn;
        if (i2 == 0) {
            i = 8;
        }
        view.setVisibility(i);
    }

    private void zzbpf() {
        int i;
        int connectionStatusCode;
        try {
            startActivityForResult(new IntentBuilder(2).setBoundsBias(this.agp).setFilter(this.agq).zzkv(this.ago.getText().toString()).zzuk(1).build(getActivity()), 30421);
            i = -1;
        } catch (Throwable e) {
            connectionStatusCode = e.getConnectionStatusCode();
            Log.e("Places", "Could not open autocomplete activity", e);
            i = connectionStatusCode;
        } catch (Throwable e2) {
            connectionStatusCode = e2.errorCode;
            Log.e("Places", "Could not open autocomplete activity", e2);
            i = connectionStatusCode;
        }
        if (i != -1) {
            GoogleApiAvailability.getInstance().showErrorDialogFragment(getActivity(), i, 30422);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 30421) {
            if (i2 == -1) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), intent);
                if (this.agr != null) {
                    this.agr.onPlaceSelected(place);
                }
                setText(place.getName().toString());
            } else if (i2 == 2) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), intent);
                if (this.agr != null) {
                    this.agr.onError(status);
                }
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R$layout.place_autocomplete_fragment, viewGroup, false);
        this.agm = inflate.findViewById(R$id.place_autocomplete_search_button);
        this.agn = inflate.findViewById(R$id.place_autocomplete_clear_button);
        this.ago = (EditText) inflate.findViewById(R$id.place_autocomplete_search_input);
        OnClickListener c34481 = new C34481(this);
        this.agm.setOnClickListener(c34481);
        this.ago.setOnClickListener(c34481);
        this.agn.setOnClickListener(new C34492(this));
        zzbpe();
        return inflate;
    }

    public void onDestroyView() {
        this.agm = null;
        this.agn = null;
        this.ago = null;
        super.onDestroyView();
    }

    public void setBoundsBias(@Nullable LatLngBounds latLngBounds) {
        this.agp = latLngBounds;
    }

    public void setFilter(@Nullable AutocompleteFilter autocompleteFilter) {
        this.agq = autocompleteFilter;
    }

    public void setHint(CharSequence charSequence) {
        this.ago.setHint(charSequence);
        this.agm.setContentDescription(charSequence);
    }

    public void setOnPlaceSelectedListener(PlaceSelectionListener placeSelectionListener) {
        this.agr = placeSelectionListener;
    }

    public void setText(CharSequence charSequence) {
        this.ago.setText(charSequence);
        zzbpe();
    }
}
