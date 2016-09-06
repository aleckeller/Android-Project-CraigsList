package com.example.listview;

import android.app.AlertDialog;

public class BikeData {
    final String COMPANY;
    final String MODEL;
    final double PRICE;
    final String LOCATION;
    final String DATE;
    final String DESCRIPTION;
    final String LINK;
    final String PICTURE;

    @Override
    public String toString() {
        // TODO figure out how to print all bikedata out for dialogs
        String display = "Company: " + COMPANY + "\n" + "Model: " + MODEL + "\n" + "Price: $" + PRICE + "\n" + "Location: " + LOCATION + "\n" + "Date Listed: " + DATE
                + "\n" + "Description: " + DESCRIPTION + "\n" + "Link: " + LINK;
        return display;
    }

    private BikeData(Builder b) {
        this.COMPANY = b.Company;
        this.LINK = b.Link;
        this.DESCRIPTION = b.Description;
        this.DATE = b.Date;
        this.LOCATION = b.Location;
        this.MODEL = b.Model;
        this.PICTURE = b.Picture;
        this.PRICE = b.Price;
    }

    public static class Builder {
        final String Company;
        final String Model;
        final Double Price;
        String Description;
        String Location;
        String Date;
        String Picture;
        String Link;

        Builder(String Company, String Model, Double Price) {
            this.Company = Company;
            this.Model = Model;
            this.Price = Price;
        }


        Builder setDescription(String Description) {
            this.Description = Description;
            return this;
        }

        Builder setLocation(String Location) {
            this.Location = Location;
            return this;
        }

        Builder setDate(String Date) {
            this.Date = Date;
            return this;
        }

        Builder setPicture(String Picture) {
            this.Picture = Picture;
            return this;
        }

        Builder setLink(String Link) {
            this.Link = Link;
            return this;
        }

        public BikeData build() {
            return new BikeData(this);
        }
    }
}
