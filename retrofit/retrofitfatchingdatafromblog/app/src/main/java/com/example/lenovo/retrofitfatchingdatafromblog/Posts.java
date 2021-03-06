
package com.example.lenovo.retrofitfatchingdatafromblog;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Posts {

    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;
    @SerializedName("selfLink")
    @Expose
    private String selfLink;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Posts() {
    }

    /**
     * 
     * @param totalItems
     * @param selfLink
     */
    public Posts(Integer totalItems, String selfLink) {
        super();
        this.totalItems = totalItems;
        this.selfLink = selfLink;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

}
