package com.github.deansquirrel.tools.common;

public interface ComparableDO extends Comparable<ComparableDO> {

    String getCompareKey();

    @Override
    default int compareTo(ComparableDO o) {
        return this.getCompareKey().compareTo(o.getCompareKey());
    }


}
