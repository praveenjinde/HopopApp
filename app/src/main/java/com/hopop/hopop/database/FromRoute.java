package com.hopop.hopop.database;

import android.os.Parcel;
import android.os.Parcelable;

import com.facebook.stetho.json.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;
import com.orm.query.Condition;
import com.orm.query.Select;

/**
 * Created by HopOp on 6/4/2016.
 */
@Table
public class FromRoute extends SugarRecord implements Parcelable {
    @SerializedName("stop_location")
    private String stopLocation;

    @Override
    public String toString() {
        return "FromRoute{" +
                "stopLocation='" + stopLocation + '\'' +
                '}';
    }

    public String getStopLocation() {
        return stopLocation;
    }

    public void setStopLocation(String stopLocation) {
        this.stopLocation = stopLocation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.stopLocation);
    }

    public FromRoute() {
    }

    public static boolean isNew(String stopLocation){
        long count = Select.from(FromRoute.class).where(Condition.prop("stop_location").eq(stopLocation)).count();
        if(count>0){
            return false;
        }else {
            return true;
        }
    }

    protected FromRoute(Parcel in) {
        this.stopLocation = in.readString();
    }

    public static final Parcelable.Creator<FromRoute> CREATOR = new Parcelable.Creator<FromRoute>() {
        @Override
        public FromRoute createFromParcel(Parcel source) {
            return new FromRoute(source);
        }

        @Override
        public FromRoute[] newArray(int size) {
            return new FromRoute[size];
        }
    };
}
