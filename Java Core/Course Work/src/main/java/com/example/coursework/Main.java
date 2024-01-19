package com.example.coursework;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Stream;


public class Main
{
    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        IOFileInfo ioFileInfo = new IOFileInfo();
        CLI cli = new CLI();
        ProgramManager programManager = new ProgramManager();

        cli.intro();

        switch (cli.realizationMethod) {
            case "CLI" -> {
                while (cli.isContinueWorking()) {
                    cli.inputFileInfo(ioFileInfo);

                    programManager.manageInput(ioFileInfo);
                    programManager.manageOutput(ioFileInfo);

                    cli.finishOrContinue();
                }
            }
            case "UI" -> {
                ExpressionsApplication.main(args);
            }
        }
    }
}