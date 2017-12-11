package zystudio.mylib.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileUtil {

    public static void exportToFileWithFOStream(String path, byte[] data) {
        FileOutputStream fos = null;
        try {
            File file = new File(path);

            if (!file.exists()) {
                file.createNewFile();
            }

            fos = new FileOutputStream(file);
            fos.write(data);
            fos.flush();
        } catch (Exception e) {
            LogUtil.log("FileUtils export byte stream error:" + e.getMessage());
        } finally {
            closeOutputStream(fos);
        }
    }

    /**
     * 貌似现在这个不是很好用,有一些问题
     * 
     * @param path
     * @param data
     */
    public static void exportBytesToFileWithBOStream(String path, byte[] data) {

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            File file = new File(path);

            if (!file.exists()) {
                file.createNewFile();
            }

            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(data);
        } catch (Exception e) {
            LogUtil.log("FileUtils export byte stream error:" + e.getMessage());
        } finally {
            closeOutputStream(fos);
            closeOutputStream(bos);
        }
    }

    private static void closeOutputStream(OutputStream ostream) {
        try {
            if (ostream != null) {
                ostream.close();
            }
        } catch (Exception e) {
            LogUtil.log("closeOutputStream exception:" + ostream);
        }
    }

}
