package com.jhilan.datagenerators;

import java.io.File;
import java.util.List;

import org.apache.commons.io.filefilter.IOFileFilter;

import com.jhilan.config.DataSetConfig;

/**
 * Created by jhilanalkarawi on 3/28/17.
 */
public class CanReadTextFilesFilter implements IOFileFilter {
    private final List<DataSetConfig.FilesFormats> filesFormats;

    public CanReadTextFilesFilter(List<DataSetConfig.FilesFormats> filesFormats) {
        this.filesFormats = filesFormats;
    }

    @Override
    public boolean accept(File file) {
        final String fileName = file.getName().toLowerCase();
        return file.canRead() &&
               (filesFormats.stream().anyMatch(format -> fileName.endsWith(format.name().toLowerCase())) || file.isDirectory());
    }

    @Override
    public boolean accept(final File dir, final String name) {
        final String[] list = dir.list();
        return dir.canRead() && list != null && list.length > 0;
    }
}
