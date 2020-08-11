package com.example.awningmanufacturer;

public class OfferItem {

    private String offerName;
    private String off;
    private String valid;
    private String background;

    public OfferItem(String offerName, String off, String valid, String background) {
        this.offerName = offerName;
        this.off = off;
        this.valid = valid;
        this.background = background;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOff() {
        return off;
    }

    public void setOff(String off) {
        this.off = off;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
