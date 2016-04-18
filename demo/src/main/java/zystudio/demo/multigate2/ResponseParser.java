package zystudio.demo.multigate2;

import java.io.InputStream;

public class ResponseParser {

//	public void parseResponse(InputStream stream){
//
//		// verfity first byte 
//		if( 0x73==stream.read()){
//		}
//
//		//id
//		String id;
//		id+=stream.read();
//		id+=stream.read();
//		//method
//		int method;
//		method=stream.read();
//		//urilength 
//		byte[]  uriLengthBuffer=new byte[4];
//		stream.read(uriLengthBuffer);
//		int uriLength= toInt(uriLengthBuffer).
//				//uriData
//				byte[] uriBuffer=new byte[uriLength];
//		stream.read(uriBuffer);
//		//headerLength
//		byte[] headerLength=new byte[4];
//		stream.read(headerLength);
//		//header data
//		byte[] headerbuf=new byte[headerLength];
//		stream.read(headerbuf);
//
//		//--------------body是分段的,得有一个循环----------//
//
//
//		byte[] bodyLengthBuf=new byte[4];  //这个取4byte数组的length可以一直复用的   
//		stream.read(bodyLengthBuf);
//		int bodyLength=toInt( bodyLengthBuf);
//		while(bodyLength!=-1){
//			//body lenght
//			//
//			byte[] bodyBuf=new byte[bodyLength];
//			stream.read(bodyBuf);
//
//			stream.read(bodyLengthBuf);
//			bodyLength=toInt( bodyLengthBuf);
//		}
//	}
}
