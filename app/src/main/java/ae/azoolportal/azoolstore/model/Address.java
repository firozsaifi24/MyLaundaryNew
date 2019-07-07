package ae.azoolportal.azoolstore.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {

    private String id;
    private String addressType;
    private String buldingNameAndNumber;
    private String street;
    private String landmark;
    private String city;
    private String state;
    private String Country;
    private String zipCode;

    public Address() {
    }

    public Address(String id, String addressType, String buldingNameAndNumber, String street, String landmark, String city, String state, String country, String zipCode) {
        this.id = id;
        this.addressType = addressType;
        this.buldingNameAndNumber = buldingNameAndNumber;
        this.street = street;
        this.landmark = landmark;
        this.city = city;
        this.state = state;
        Country = country;
        this.zipCode = zipCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getBuldingNameAndNumber() {
        return buldingNameAndNumber;
    }

    public void setBuldingNameAndNumber(String buldingNameAndNumber) {
        this.buldingNameAndNumber = buldingNameAndNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    protected Address(Parcel in) {
        id = in.readString();
        addressType = in.readString();
        buldingNameAndNumber = in.readString();
        street = in.readString();
        landmark = in.readString();
        city = in.readString();
        state = in.readString();
        Country = in.readString();
        zipCode = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(addressType);
        dest.writeString(buldingNameAndNumber);
        dest.writeString(street);
        dest.writeString(landmark);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(Country);
        dest.writeString(zipCode);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
