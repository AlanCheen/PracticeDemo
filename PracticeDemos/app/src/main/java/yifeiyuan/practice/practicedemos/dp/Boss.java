/*
 * Copyright (C) 2015, 程序亦非猿
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package yifeiyuan.practice.practicedemos.dp;

/**
 * Created by 程序亦非猿 on 16/1/18.
 */
public class Boss {

    Worker mWorker;

    public int plus(int a, int b) {
        return mWorker.plus(a, b);
    }

    public void plus(int a, int b, Callback callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (null != callback) {
                    callback.onFinished(mWorker.plus(a, b));
                }
            }
        }).start();

    }


    public interface Callback {
        void onFinished(int result);
    }
}
