package zystudio.cases.dataprocess.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import android.util.Log;

public class CaseRandomAccessFile {

	private static CaseRandomAccessFile sCase;
    private static final String FILE_NAME="/sdcard/randomfiledemo.txt";
    private static final String NAME_1="SAM";
    private static final String NAME_2="ABCDEFGHIGK";

	public static void showDemo() {
		if (sCase == null) {
			sCase = new CaseRandomAccessFile();
		}
//		sCase.createGivenSizeFile();
        sCase.runDemoRuntime();
	}

	private CaseRandomAccessFile() {

	}

    /**
     *http://blog.csdn.net/funneies/article/details/9311027  这个还没弄完呢
     */
    private void runDemoRuntime(){
        write();
        read();
        randomWrite();
//        rmFile();
    }
	
    private void randomWrite(){
    	try{
    		RandomAccessFile raf=new RandomAccessFile(FILE_NAME,"rw");
    		raf.seek(8*4);
    		Log.i("ZYStudio", "randomWrite filePointer:"+raf.getFilePointer());
    		raf.write(NAME_1.getBytes());
    		raf.writeInt(102);
    		raf.close();
    	}catch(Exception e){ }
    }
    
    private void read() {
        try{
        	RandomAccessFile raf=new RandomAccessFile(FILE_NAME,"rw");
        	raf.seek(8*1);
            byte[] buf=new byte[4];
            raf.read(buf);
            String name=new String(buf);
            int age=raf.readInt();
            Log.i("ZYStudio", "read name and age:"+name+"|"+age);
            Log.i("ZYStudio", "pos2:"+raf.getFilePointer());
            raf.close();
        }catch(Exception e){
        	Log.i("ZYStudio","read function exception:"+e.getMessage());
        }
    }
    
    private void write(){
        try{
        RandomAccessFile raf=new RandomAccessFile(FILE_NAME,"rw");
        raf.write(NAME_2.getBytes());
        //擦97到了里边成了a了..
        raf.writeInt(97);
        raf.close();
        }catch(Exception e){
        	Log.i("ZYStudio","write function exception:"+e.getMessage());
        }
    }
    
    private void rmFile(){
        File file=new File(FILE_NAME);
        if(file.exists()){
        	file.delete();
        }
    }
    
	/**
     * 创建一个指定大小的文件，这里边成功创建了一个500M的文件
	 * http://stackoverflow.com/questions/245251/create-file-with-given-size-in-
	 * java
	 */
	private void createGivenSizeFile() {
		try {
            //成功实现了一个５００Ｍ的文件
			RandomAccessFile file = new RandomAccessFile(
					"/sdcard/myrandomefile.txt", "rw");
			file.setLength(500 * 1024 * 1024);
            Log.i("ZYStudio", "createGivenSizeFile finish");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.i("ZYStudio", "createGivenSizeFile filenotFoundException");
		} catch (IOException e) {
			Log.i("ZYStudio", "createGivenSizeFile IOException"+e.getMessage());
			e.printStackTrace();
		}
	}
}
