package yifeiyuan.practice.practicedemos.ipc;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 程序亦非猿 on 15/12/3.
 */
public class Book implements Parcelable{


    public String bookName;
    public int bookId;

    protected Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }
}
