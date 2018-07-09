package com.whb.aidlremoteservice.aidl;

interface BookQuery {
	Bundle queryBookInfoById(String id);
	Bundle[] queryBookInfoByName(String name);
	Bundle[] queryBookInfoByAuthor(String author);
}