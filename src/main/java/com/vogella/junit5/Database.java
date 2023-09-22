package com.vogella.junit5;

public class Database {

    public boolean isAvailable() {
    	int x=1/0;
    	System.out.println("hello");
        // currently not implemented, as this is just demo used in a software test
        return false;
    }
    public int getUniqueId() {
        return 42;
    }
}