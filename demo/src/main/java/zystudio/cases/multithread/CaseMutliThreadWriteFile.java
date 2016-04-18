package zystudio.cases.multithread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CaseMutliThreadWriteFile {

    private static CaseMutliThreadWriteFile sCase;
    private static Lock sLock = new ReentrantLock();
    private static int sState = 0;
    private static ArrayList<Integer> mIntegerList = new ArrayList<Integer>();
    private static ArrayList<String> mFileList = new ArrayList<String>();

    public final static CaseMutliThreadWriteFile obtain() {
        if (sCase == null) {
            sCase = new CaseMutliThreadWriteFile();
        }
        return sCase;
    }

    public void work() {
        test();
    }

    public static void set(int value) {
        if (mIntegerList.size() > 4) {
            sLock.lock();
        }
        mIntegerList.add(value);
        sLock.unlock();
    }

    public static int get() {
        if (mIntegerList.size() == 0) {
            sLock.lock();
        }
        sLock.unlock();
        return mIntegerList.get(0);
    }

    public void test() {
        for (int i = 1; i < 10000; i++) {
            mFileList.add(i + "");
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this1");
                saveObject(mFileList, "/sdcard/", "a.txt");

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this2");
                saveObject(mFileList, "c:/", "a.txt");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this3");
                saveObject(mFileList, "c:/", "a.txt");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this4");
                saveObject(mFileList, "c:/", "a.txt");
            }
        }).start();
    }

    public static String getDir() {
        return "c:/";
    }

    public static void saveObject(Object object, String path, String name) {
        System.out.println(path + name);
        FileOutputStream f_out = null;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            f_out = new FileOutputStream(path + name);
            ObjectOutputStream s = new ObjectOutputStream(f_out);
            System.out.println("write " + name + " start");
            s.writeObject(object);
            System.out.println("write " + name + " finish");

            s.flush();
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (f_out != null) {
                try {
                    f_out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(path + name + " finish");
    }

    public static Object readObject(String path, String name) {
        FileInputStream f_in = null;
        Object object = null;
        String filePath = path + name;
        if (!isFileExists(filePath)) {
            System.out.println("no File  " + filePath);
            return null;
        }
        try {
            f_in = new FileInputStream(filePath);
            ObjectInputStream s = new ObjectInputStream(f_in);
            object = s.readObject();
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (f_in != null) {
                try {
                    f_in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }

    public static boolean isFileExists(String path) {
        return new File(path).exists();
    }

    static class ThreadA extends Thread {

        @Override
        public void run() {
            while (true) {
                sLock.lock();
                if (sState % 2 == 0) {
                    System.out.println("A");
                    sState++;
                }
                sLock.unlock();
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            while (true) {
                sLock.lock();
                if (sState % 2 == 1) {
                    System.out.println("B");
                    sState++;
                }
                sLock.unlock();
            }
        }
    }

}
