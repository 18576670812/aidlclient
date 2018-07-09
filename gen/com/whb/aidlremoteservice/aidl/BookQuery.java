/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\tools\\android-sdk\\adt-bundle-windows-x86-20140321\\eclipse\\eclipse_workspace\\aidlclient\\src\\com\\whb\\aidlremoteservice\\aidl\\BookQuery.aidl
 */
package com.whb.aidlremoteservice.aidl;
public interface BookQuery extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.whb.aidlremoteservice.aidl.BookQuery
{
private static final java.lang.String DESCRIPTOR = "com.whb.aidlremoteservice.aidl.BookQuery";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.whb.aidlremoteservice.aidl.BookQuery interface,
 * generating a proxy if needed.
 */
public static com.whb.aidlremoteservice.aidl.BookQuery asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.whb.aidlremoteservice.aidl.BookQuery))) {
return ((com.whb.aidlremoteservice.aidl.BookQuery)iin);
}
return new com.whb.aidlremoteservice.aidl.BookQuery.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_queryBookInfoById:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.Bundle _result = this.queryBookInfoById(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_queryBookInfoByName:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.Bundle[] _result = this.queryBookInfoByName(_arg0);
reply.writeNoException();
reply.writeTypedArray(_result, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
return true;
}
case TRANSACTION_queryBookInfoByAuthor:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.Bundle[] _result = this.queryBookInfoByAuthor(_arg0);
reply.writeNoException();
reply.writeTypedArray(_result, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.whb.aidlremoteservice.aidl.BookQuery
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public android.os.Bundle queryBookInfoById(java.lang.String id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.Bundle _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(id);
mRemote.transact(Stub.TRANSACTION_queryBookInfoById, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.os.Bundle.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public android.os.Bundle[] queryBookInfoByName(java.lang.String name) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.Bundle[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
mRemote.transact(Stub.TRANSACTION_queryBookInfoByName, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArray(android.os.Bundle.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public android.os.Bundle[] queryBookInfoByAuthor(java.lang.String author) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.Bundle[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(author);
mRemote.transact(Stub.TRANSACTION_queryBookInfoByAuthor, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArray(android.os.Bundle.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_queryBookInfoById = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_queryBookInfoByName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_queryBookInfoByAuthor = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public android.os.Bundle queryBookInfoById(java.lang.String id) throws android.os.RemoteException;
public android.os.Bundle[] queryBookInfoByName(java.lang.String name) throws android.os.RemoteException;
public android.os.Bundle[] queryBookInfoByAuthor(java.lang.String author) throws android.os.RemoteException;
}
