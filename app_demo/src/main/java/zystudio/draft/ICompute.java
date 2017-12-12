package zystudio.draft;

import android.os.IBinder;

/**
 * Created by zylab on 2017/12/10.
 */

public interface ICompute extends android.os.IInterface{
//
//    //----------------Cls Stub----------------//
//    public static abstract class Stub extends android.os.Binder implements com.example.test.app.ICompute {
//
//        private static final java.lang.String DESCRIPTOR="com.example.test.app.ICompute";
//
//        public Stub() {
//            this.attachInterface(this,DESCRIPTOR);
//        }
//
//        public static com.example.test.app.ICompute asInterface(android.oi.IBinder obj){
//            if ((obj == null)) {
//                return null;
//            }
//
//            android.os.IInterface iin=obj.queryLocalInterface(DESCRIPTOR);
//            if(((iin!=null)&&(iin instanceof com.example.test.app.ICompute))){
//               return ((com.example.test.app.ICompute) iin);
//            }
//            return new com.example.test.app.ICompute.Stub.Proxy(obj);
//        }
//
//
//        @Override
//        public android.os.IBinder asBinder(){
//            return this;
//        }
//
//        public boolean onTransact(int code ,android.os.Parcel data, android.os.Parcel reply, int flags )
//                throws android.os.RemoteException {
//            switch (code){
//                case INTERFACE_TRANSACTION: {
//                    reply.writeString(DESCRIPTOR);
//                    return true;
//                }
//
//                case TRANSACTION_add: {
//                    data.enforceInterface(DESCRIPTOR);
//                    int _arg0;
//                    _arg0=data.readInt();
//                    int _arg1;
//                    //....
//                    return true;
//                }
//            }
//            return super.onTransact(code,data,reply,flags);
//        }
//
//        //-----------Proxy----------------//
//        private static class Proxy implements com.example.test.app.ICompute{
//           private android.os.IBinder mRemote;
//
//           Proxy(android.os.IBinder remote){
//              mRemote=remote;
//           }
//
//           @Override
//           public android.os.IBinder asBinder(){
//               return mRemote;
//           }
//
//           public java.lang.String  getInterfaceDescriptor(){
//               return DESCRIPTOR;
//           }
//
//           public int add(int a ,int b ) throws android.os.RemoteException {
//               return _result;
//           }
//        }
//        //---------Proxy End ------------//
//        static final int TRANSACTION_add=(IBinder.FIRST_CALL_TRANSACTION+0);
//    }
//
//    public int add(int a,int b) throws android.os.RemoteException;
}
