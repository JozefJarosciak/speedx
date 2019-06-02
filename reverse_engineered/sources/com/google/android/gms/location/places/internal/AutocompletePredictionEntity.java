package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Collections;
import java.util.List;

public class AutocompletePredictionEntity extends AbstractSafeParcelable implements AutocompletePrediction {
    public static final Creator<AutocompletePredictionEntity> CREATOR = new zza();
    private static final List<SubstringEntity> afj = Collections.emptyList();
    final String aeL;
    final List<Integer> aeh;
    final String afk;
    final List<SubstringEntity> afl;
    final int afm;
    final String afn;
    final List<SubstringEntity> afo;
    final String afp;
    final List<SubstringEntity> afq;
    final int mVersionCode;

    public static class SubstringEntity extends AbstractSafeParcelable {
        public static final Creator<SubstringEntity> CREATOR = new zzu();
        final int mLength;
        final int mOffset;
        final int mVersionCode;

        public SubstringEntity(int i, int i2, int i3) {
            this.mVersionCode = i;
            this.mOffset = i2;
            this.mLength = i3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SubstringEntity)) {
                return false;
            }
            SubstringEntity substringEntity = (SubstringEntity) obj;
            return zzaa.equal(Integer.valueOf(this.mOffset), Integer.valueOf(substringEntity.mOffset)) && zzaa.equal(Integer.valueOf(this.mLength), Integer.valueOf(substringEntity.mLength));
        }

        public int getLength() {
            return this.mLength;
        }

        public int getOffset() {
            return this.mOffset;
        }

        public int hashCode() {
            return zzaa.hashCode(Integer.valueOf(this.mOffset), Integer.valueOf(this.mLength));
        }

        public String toString() {
            return zzaa.zzz(this).zzg("offset", Integer.valueOf(this.mOffset)).zzg("length", Integer.valueOf(this.mLength)).toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzu.zza(this, parcel, i);
        }
    }

    AutocompletePredictionEntity(int i, String str, List<Integer> list, int i2, String str2, List<SubstringEntity> list2, String str3, List<SubstringEntity> list3, String str4, List<SubstringEntity> list4) {
        this.mVersionCode = i;
        this.aeL = str;
        this.aeh = list;
        this.afm = i2;
        this.afk = str2;
        this.afl = list2;
        this.afn = str3;
        this.afo = list3;
        this.afp = str4;
        this.afq = list4;
    }

    public static AutocompletePredictionEntity zza(String str, List<Integer> list, int i, String str2, List<SubstringEntity> list2, String str3, List<SubstringEntity> list3, String str4, List<SubstringEntity> list4) {
        return new AutocompletePredictionEntity(0, str, list, i, (String) zzab.zzaa(str2), list2, str3, list3, str4, list4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AutocompletePredictionEntity)) {
            return false;
        }
        AutocompletePredictionEntity autocompletePredictionEntity = (AutocompletePredictionEntity) obj;
        return zzaa.equal(this.aeL, autocompletePredictionEntity.aeL) && zzaa.equal(this.aeh, autocompletePredictionEntity.aeh) && zzaa.equal(Integer.valueOf(this.afm), Integer.valueOf(autocompletePredictionEntity.afm)) && zzaa.equal(this.afk, autocompletePredictionEntity.afk) && zzaa.equal(this.afl, autocompletePredictionEntity.afl) && zzaa.equal(this.afn, autocompletePredictionEntity.afn) && zzaa.equal(this.afo, autocompletePredictionEntity.afo) && zzaa.equal(this.afp, autocompletePredictionEntity.afp) && zzaa.equal(this.afq, autocompletePredictionEntity.afq);
    }

    public /* synthetic */ Object freeze() {
        return zzboe();
    }

    public CharSequence getFullText(@Nullable CharacterStyle characterStyle) {
        return zzc.zza(this.afk, this.afl, characterStyle);
    }

    @Nullable
    public String getPlaceId() {
        return this.aeL;
    }

    public List<Integer> getPlaceTypes() {
        return this.aeh;
    }

    public CharSequence getPrimaryText(@Nullable CharacterStyle characterStyle) {
        return zzc.zza(this.afn, this.afo, characterStyle);
    }

    public CharSequence getSecondaryText(@Nullable CharacterStyle characterStyle) {
        return zzc.zza(this.afp, this.afq, characterStyle);
    }

    public int hashCode() {
        return zzaa.hashCode(this.aeL, this.aeh, Integer.valueOf(this.afm), this.afk, this.afl, this.afn, this.afo, this.afp, this.afq);
    }

    public boolean isDataValid() {
        return true;
    }

    public String toString() {
        return zzaa.zzz(this).zzg("placeId", this.aeL).zzg("placeTypes", this.aeh).zzg("fullText", this.afk).zzg("fullTextMatchedSubstrings", this.afl).zzg("primaryText", this.afn).zzg("primaryTextMatchedSubstrings", this.afo).zzg("secondaryText", this.afp).zzg("secondaryTextMatchedSubstrings", this.afq).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public AutocompletePrediction zzboe() {
        return this;
    }
}
