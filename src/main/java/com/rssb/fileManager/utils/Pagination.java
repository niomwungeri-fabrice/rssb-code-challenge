package com.rssb.fileManager.utils;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toMap;
import static java.lang.Math.min;

public class Pagination {
    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {

        if(pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if(sourceList == null || sourceList.size() < fromIndex){
            return Collections.emptyList();
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }
}
