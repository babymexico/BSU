package com.example.coursework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PlainTextWorker {
    private String fileName;
    private Vector<String> gainData;
    public PlainTextWorker(String fileName) {
        this.fileName = fileName;
    }

    public Vector<String> getGainData() {
        return gainData;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> readingFromPlain(String fileName)
    {
        List<String> gainData = new ArrayList<>();
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(fileName));

            String tmpLine;
            while((tmpLine = br.readLine()) != null)
            {
                String[] expressions = tmpLine.split(";");
                for (int i = 0; i < expressions.length; i++)
                    gainData.add(expressions[i]);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error" + e);
        }
        finally
        {
            try
            {
                if (br != null)
                {
                    br.close();
                }
            }
            catch (IOException e)
            {
                System.out.println("Error" + e);
            }
        }

        return gainData;
    }

    public void writeInPlain(String outFileName, String integerVector)
    {
        try(FileWriter fileWriter = new FileWriter(outFileName))
        {
            fileWriter.write(integerVector);
        }
        catch (IOException exp)
        {
            System.out.println("Error" + exp);
        }
    }
}