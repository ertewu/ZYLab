package zystudio.cases.javabase.io;


import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

/**
 * 主要用来学习 Java I/O中的 各种Reader与Writer的用法
 * 这里边
 * 目前主要看到 BufferedReader,FileReader
 */
public class CaseIOReader {

    public void work() {
        showStringReaderCase();
//        showContrastCase1();
//        showInputStreamReaderCase();
    }

    private void showStringReaderCase(){
        try{
            String input = "Input String... ";
            StringReader stringReader = new StringReader(input);

            int data = stringReader.read();
            while(data != -1) {
                //do something with data...
                char ch=(char) data;
                Log.i("ZYStudio","StringReaderCase:"+ch);
                data = stringReader.read();
            }
            stringReader.close();
        }catch(Exception e){

        }
    }

    //----------------------------------------------------------------------------------------------

    private void showInputStreamReaderCase(){
        /**
         * http://tutorials.jenkov.com/java-io/inputstreamreader.html
         * 这个与底下的FileReader比,代码已经多了一层stream的暴露了..呵呵,但还是可以比较方便的用的
         * </br>
         * 通过上篇文章,我理解的流程实际上是这样的..文件的里的内容,是采用utf-8字符集的.
         * 但是用Reader转换成char的过程中,转成了字符集无关的 char,char只是unicode字符编码的代表..
         * Reader作为i/o操作时,已经帮我们默认转了..
         */
        try{
            InputStream inputStream = new FileInputStream("/sdcard/0_MyDir/mingchao_chapter1.txt");
            Reader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            int data = inputStreamReader.read();
            while(data != -1){
                char theChar = (char) data;
                Log.i("ZYStudio","InputStreamReader:"+theChar);
                data = inputStreamReader.read();
            }

            inputStreamReader.close();
        }catch (Exception e){

        }
    }


    //--------------------------------------基本的FileReader与read函数使用------------------

    private void showContrastCase1(){
        /**
         * 首先明确的一点是,i/o的文件,都是字符集编码, 即使Reader返回的是字符集无关的char,但也是内部作了字符集向字符编码的转换.
         * FileReader的实现,就是InputStreamReader,默认采用了系统的字符集
         *
         *read函数每次吐出一个unicode编码,而因为英文字母unicode编码与encoding,本来就可以表示的,所以default.txt里边打出来的都是对的；</b>
         *而demo.txt都是中文的,1byte是不够的；所以转成char之后,是乱码...
         * 在Java中 char所对应的 size是16bits也就是2byte.. 所以char中其实就是一个字符的unicode码；但与utf-8的字符编码没任何关系..结果显示乱码了
         *
         * 还是要讲一步理解字符集与字符编码的区别,在java中的运用,各自在实际解决问题中的重点
         */
        showFileReaderEnglish();
       showFileReaderChEnHybrid();
    }

    private void showFileReaderEnglish(){
        try {
            int LIMIT=1000;
            //default.txt是一个纯英文
            Reader reader=new FileReader("/sdcard/0_MyDir/default.txt");
            int data=reader.read();
            int count=0;
            while(data!=-1 && count<LIMIT){
                char dataChr=(char) data;
                Log.i("ZYStudio","dataChar is:"+dataChr);

                count++;
                data=reader.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showFileReaderChEnHybrid(){
        try {
            int LIMIT=100;
            //mingchao_chapter1.txt主要是汉语,夹杂些英文和数字
            Reader reader=new FileReader("/sdcard/0_MyDir/mingchao_chapter1.txt");
            int data=reader.read();
            int count=0;
            while(data!=-1 && count<LIMIT){

                char dataChr=(char) data;
                Log.i("ZYStudio","dataChar before encoding is:"+dataChr);

                //一个char是2个byte,但是一个char转换成 byte[] 却不很简单..
                byte[] byteArray=singleCharToByteArray(dataChr);
                String str=new String(byteArray,"UTF-8");
                //这个也是乱码,说明char转string时,string构造函数中的byte[]并非char的byte,而必须是string用字符编码打成的byte
                Log.i("ZYStudio","dataChar convert to string 1 is:"+str);

                str=String.valueOf(dataChr);
//                str=Character.toString(dataChr);
                Log.i("ZYStudio","dataChar write convert to string 2 is:"+str);

                count++;
                data=reader.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 这个是我见到的所有方法中最简单的,果然还是移位
     */
    private static byte[]  singleCharToByteArray(char a){
        byte[] byteArray=new byte[2];
        byteArray[0]=(byte)(a>>8);
        byteArray[1]=(byte) (a);
        return byteArray;
    }

}
