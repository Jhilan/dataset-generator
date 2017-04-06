package com.jhilan.datagenerators;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import com.jhilan.config.BodyTextField;
import com.jhilan.exception.ConfigValidationException;
import com.jhilan.exception.FileReadException;
import com.jhilan.config.DataSetConfig;

/**
 * Created by jhilanalkarawi on 3/28/17.
 */
public class BodyTextIterator implements Iterator<String> {
    private final int numberOfDocs;
    private final int documentSize;
    private final List<File> filesList;
    private final BodyTextField textField;

    private List<String> currentFileInMemory;
    private final AtomicInteger count = new AtomicInteger(0);

    private static final Random RANDOM_GENERATOR = new Random();

    public BodyTextIterator(final BodyTextField textField, final DataSetConfig dataSetConfig) {
        numberOfDocs = dataSetConfig.getNumberOfDocuments();
        documentSize = dataSetConfig.getDocumentSize();

        final File baseTextDir = getBaseDirPath(dataSetConfig.getTextBaseDirectory());
        final IOFileFilter filter = new CanReadTextFilesFilter(dataSetConfig.getFilesFormatsToAccept());
        filesList = cleanAndGetFilesList(FileUtils.listFilesAndDirs(baseTextDir, filter, filter));

        currentFileInMemory = loadNextFileLines(filesList);
        this.textField = textField;
    }

    @Override
    public boolean hasNext() {
        return count.get() < numberOfDocs;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException("number of requested document reached.");
        }

        final StringBuilder doc = new StringBuilder(documentSize);

        while (doc.length() < documentSize) {
            if (currentFileInMemory.isEmpty()) {
                currentFileInMemory = loadNextFileLines(filesList);
            }
            doc.append(getNextLine()).append('\n');
        }

        count.incrementAndGet();
        return doc.toString();
    }

    //----- private -----
    private String getNextLine() {
        if (currentFileInMemory.size() == 1) {
            return currentFileInMemory.get(0);
        }

        if (textField.isRandomText()) {
            return currentFileInMemory.remove(RANDOM_GENERATOR.nextInt(currentFileInMemory.size() - 1));
        } else {
            return currentFileInMemory.remove(0);
        }
    }

    private static File getBaseDirPath(final String baseTextDirPath) {
        final File baseTextDir = new File(baseTextDirPath);
        if (!baseTextDir.exists() || !baseTextDir.isDirectory()) {
            throw new ConfigValidationException("textBaseDirectory is empty or does not exist.");
        }
        return baseTextDir;
    }

    private static List<String> loadNextFileLines(final List<File> filesList) {
        final List<String> lines;
        File nextFile = null;
        try {
            nextFile = getNextFile(filesList);
            lines = FileUtils.readLines(getNextFile(filesList), Charset.defaultCharset());
        } catch (final IOException e) {
            throw new FileReadException("could not load file: " + nextFile.getName(), e);
        }
        return lines;
    }

    private static File getNextFile(final List<File> files) {
        if (files.size() == 1) {
            return files.get(0);
        }
        final File nextFile = files.get(RANDOM_GENERATOR.nextInt(files.size() - 1));
        return nextFile;
    }

    private static List<File> cleanAndGetFilesList(final Collection<File> files) {
        final List<File> filteredFiles = files.stream().filter(file -> !file.isDirectory()).collect(Collectors.toList());

        if (filteredFiles.size() < 1) {
            throw new ConfigValidationException("textBaseDirectory does not contain files matching the FilesFormats config.");
        }
        return filteredFiles;
    }
}
