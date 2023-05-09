
package com.cowin.bean;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class CalenderPincode {

    @SerializedName("centers")
    @Expose
    private List<Center> centers = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CalenderPincode() {
    }

    /**
     * 
     * @param centers
     */
    public CalenderPincode(List<Center> centers) {
        super();
        this.centers = centers;
    }

    public List<Center> getCenters() {
        return centers;
    }

    public void setCenters(List<Center> centers) {
        this.centers = centers;
    }

}
