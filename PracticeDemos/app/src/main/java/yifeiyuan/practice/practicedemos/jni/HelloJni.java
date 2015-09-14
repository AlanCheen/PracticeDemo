package yifeiyuan.practice.practicedemos.jni;

/**
 * Created by alanchen on 15/9/2.
 */
public class HelloJni {

    static {
        System.loadLibrary("Practice");
    }

    public static native String helloJni();
}
