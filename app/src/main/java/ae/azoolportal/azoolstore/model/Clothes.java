package ae.azoolportal.azoolstore.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Clothes implements Parcelable {

    private String id;
    private String title;
    private long price;
    private long priceWithQuantity;
    private long quantity;
    private String image;

    public Clothes() {
    }

    public Clothes(String id, String title, long price, long priceWithQuantity, long quantity, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.priceWithQuantity = priceWithQuantity;
        this.quantity = quantity;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPriceWithQuantity() {
        return priceWithQuantity;
    }

    public void setPriceWithQuantity(long priceWithQuantity) {
        this.priceWithQuantity = priceWithQuantity;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    protected Clothes(Parcel in) {
        id = in.readString();
        title = in.readString();
        price = in.readLong();
        priceWithQuantity = in.readLong();
        quantity = in.readLong();
        image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeLong(price);
        dest.writeLong(priceWithQuantity);
        dest.writeLong(quantity);
        dest.writeString(image);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Clothes> CREATOR = new Parcelable.Creator<Clothes>() {
        @Override
        public Clothes createFromParcel(Parcel in) {
            return new Clothes(in);
        }

        @Override
        public Clothes[] newArray(int size) {
            return new Clothes[size];
        }
    };
}