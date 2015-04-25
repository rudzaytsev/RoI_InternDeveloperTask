package com.roi.intership.domain;

import java.nio.file.Path;

/**
 * Created by rudolph on 25.04.15.
 */
public class PackageTask implements Runnable {

    Path file;

    public PackageTask(Path file){
        this.file = file;
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

    }
}
