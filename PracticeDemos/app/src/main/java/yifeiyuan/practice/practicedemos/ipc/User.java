package yifeiyuan.practice.practicedemos.ipc;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 程序亦非猿 on 15/12/3.
 */
public class User implements Parcelable {
    private String userName;
    private int classId;
    private Book mBook;

    protected User(Parcel in) {
        userName = in.readString();
        classId = in.readInt();
        mBook = in.readParcelable(Book.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeInt(classId);
        dest.writeParcelable(mBook, flags);
    }
}
