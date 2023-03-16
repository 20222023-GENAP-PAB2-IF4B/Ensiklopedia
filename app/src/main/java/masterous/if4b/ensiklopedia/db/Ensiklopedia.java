package masterous.if4b.ensiklopedia.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ensiklopedia")
public class Ensiklopedia implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    public Ensiklopedia() {
    }

    protected Ensiklopedia(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ensiklopedia> CREATOR = new Creator<Ensiklopedia>() {
        @Override
        public Ensiklopedia createFromParcel(Parcel in) {
            return new Ensiklopedia(in);
        }

        @Override
        public Ensiklopedia[] newArray(int size) {
            return new Ensiklopedia[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
