package com.jhilan.config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DataSetConfig {
    private final int documentSize;
    private final int numberOfDocuments;
    private final String textBaseDirectory;
    private final String datasetFileName;
    private final String datasetFilePath;
    private final Format outputFormat;
    private final List<FilesFormats> filesFormatsToAccept;

    public enum Format {JSON, DAT}
    public enum FilesFormats {TXT, XML, DOCS, DOC, JSON, DAT, HTML, CSV}

    @JsonCreator
    public DataSetConfig(@JsonProperty("documentSize") final int documentSize,
                         @JsonProperty("numberOfDocuments") final int numberOfDocuments,
                         @JsonProperty("textBaseDirectory") final String textBaseDirectory,
                         @JsonProperty("datasetFielName") final String datasetFileName,
                         @JsonProperty("datasetFilePath") final String datasetFilePath,
                         @JsonProperty("format") final Format outputFormat,
                         @JsonProperty("filesFormatsToAccept") final List<FilesFormats> filesFormatsToAccept) {
        this.documentSize = documentSize;
        this.numberOfDocuments = numberOfDocuments;
        this.textBaseDirectory = textBaseDirectory;
        this.datasetFileName = datasetFileName;
        this.datasetFilePath = datasetFilePath;
        this.outputFormat = outputFormat;
        this.filesFormatsToAccept = filesFormatsToAccept;
    }

    public int getDocumentSize() {
        return documentSize;
    }

    public int getNumberOfDocuments() {
        return numberOfDocuments;
    }

    public String getTextBaseDirectory() {
        return textBaseDirectory;
    }

    public String getDatasetFileName() {
        return datasetFileName;
    }

    public String getDatasetFilePath() {
        return datasetFilePath;
    }

    public Format getOutputFormat() {
        return outputFormat;
    }

    public List<FilesFormats> getFilesFormatsToAccept() { return filesFormatsToAccept; }
}
