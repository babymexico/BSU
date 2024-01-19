package com.example.coursework;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ArchiveFileWorker {
    private static final int BUFFER_SIZE = 1024;

    public String archiveInput(String zipFileName) {
        String unpackedFile = null;

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFileName))) {
            ZipEntry entry;
            String name;

            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                System.out.printf("File name: %s \n", name);

                unpackedFile = name;

                try (FileOutputStream fout = new FileOutputStream(unpackedFile)) {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead;

                    while ((bytesRead = zin.read(buffer)) != -1) {
                        fout.write(buffer, 0, bytesRead);
                    }
                }

                zin.closeEntry();
            }
        } catch (IOException ex) {
            // Handle or log the exception appropriately
            ex.printStackTrace();
        }

        return unpackedFile;
    }

    public void archiveOutput(String fileName, String outType, String archiveType) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(
                String.format("%s.%s", fileName, archiveType)));
             FileInputStream fis = new FileInputStream(String.format("%s.%s", fileName, outType))) {

            ZipEntry entry1 = new ZipEntry(String.format("%s.%s", fileName, outType));
            zout.putNextEntry(entry1);

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                zout.write(buffer, 0, bytesRead);
            }

            zout.closeEntry();
        } catch (IOException ex) {
            // Handle or log the exception appropriately
            ex.printStackTrace();
        }
    }
}
