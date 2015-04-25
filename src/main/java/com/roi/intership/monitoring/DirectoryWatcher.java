package com.roi.intership.monitoring;

import com.roi.intership.processing.FileProcessor;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;

/**
 * Created by rudolph on 23.04.15.
 */
public class DirectoryWatcher implements Runnable {

    private Path path;

    public DirectoryWatcher(Path path) {
        this.path = path;
    }


    private void processEvent(WatchEvent<?> event){
        Kind<?> kind = event.kind();
        if(kind.equals(StandardWatchEventKinds.ENTRY_CREATE)){
            System.out.println("File created");
            Path file = (Path) event.context();
            System.out.println(file.getFileName());
            FileProcessor processor = FileProcessor.getInstance();
            processor.handle(file);
        }


    }

    @Override
    public void run() {
        WatchService watchService;
        try {
            watchService = path.getFileSystem().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            for ( ; ; ) {
                WatchKey watchKey;
                watchKey = watchService.take();
                for (final WatchEvent<?> event : watchKey.pollEvents()) {
                    this.processEvent(event);
                }

                if (!watchKey.reset()) {
                    System.out.println("No longer valid");
                    watchKey.cancel();
                    watchService.close();
                    break;
                }
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
