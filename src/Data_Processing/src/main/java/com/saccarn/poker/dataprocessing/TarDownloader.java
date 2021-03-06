package com.saccarn.poker.dataprocessing;

import org.apache.commons.io.FileUtils;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Automated way to download and store tar gz previously hosted on UNiversity of Alberta site.
 * Created by Neil on 31/01/2017.
 */
public class TarDownloader {
    private final String urlBaseString = "http://webdocs.cs.ualberta.ca/~games/poker/IRCdata/";
    private final String tar = "tar";
    private final String gz = "gz";
    private final String noLimitRegex = "nolimit\\.\\d{6}\\.tgz";

    private File filePath = new File("C:\\Data\\tar.tgz");
    private File destDir = new File("C:\\Data\\test");

    public TarDownloader() throws MalformedURLException {
    }

    public TarDownloader(File filePath0, File destDir0) {
        filePath = filePath0;
        destDir = destDir0;
    }

    public List<String> retrieveFileNames(String patternString) {
        try {
            TarGzNameRetriever tgzRetriever = new TarGzNameRetriever();
            return tgzRetriever.getFileNamesByStringPattern(patternString);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Uses Apache commons io method to download tar gz
     * @param fileIDNameString name of file to download
     */

    public void downloadTarGz(String fileIDNameString)  {
        try {
            FileUtils.copyURLToFile(new URL(urlBaseString + fileIDNameString), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Automated way to unpack tar gz folder.
     * @param fileNameString name of tar gz folder
     */
    public void unPackTarGz(String fileNameString) {
        Archiver archiver = ArchiverFactory.createArchiver(tar, gz);
        try {
            archiver.extract(filePath, destDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // RETURNS the list of file names that were downloaded.
    public List<String> retrieveGameData(String patternString) {
        List<String> fileNames= retrieveFileNames(patternString);
        for(String fileName : fileNames) {
            downloadTarGz(fileName);
            unPackTarGz(fileName.split("\\.")[1]);
        }
        return fileNames;
    }

    //RETURNS the list of file names of no limit games that were downloaded.
    public List<String> retrieveNoLimitGames() {
        return retrieveFileNames(noLimitRegex);
    }

    /**
     * Main method used to download files - they are no longer hosted there so this no longer works.
     * @param args
     * @throws IOException
     */
    public static void main(String [] args) throws IOException {

        Pattern p = Pattern.compile("7stud\\.\\d{6}\\.tgz");
        TarGzNameRetriever tgzRetriever = new TarGzNameRetriever();
        tgzRetriever.getFileNamesByStringPattern("nolimit\\.\\d{6}\\.tgz");//Pattern("7stud\\.\\d{6}\\.tgz");
        System.out.println(tgzRetriever.getFileNamesByStringPattern("nolimit\\.\\d{6}\\.tgz"));
    }
}
