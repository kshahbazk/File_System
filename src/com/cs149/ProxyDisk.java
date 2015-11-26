package com.cs149;

import java.util.ArrayList;

/**
 * Created by shahbazkhan on 11/26/15.
 *
 *  This Class will mimic a Disk Drive
 */
public class ProxyDisk {

    private Directory current;
    private ArrayList<Object> disk;

    public ProxyDisk ()
    {
        // Current always starts at root
        current.setName("root");
        // Initialize disk with root directory
        disk.add(current);
    }

    public void currentd()
    {
        System.out.println(current.getName());
    }

    public void chdir(Directory newdir) {
        current = newdir;
    }
}
