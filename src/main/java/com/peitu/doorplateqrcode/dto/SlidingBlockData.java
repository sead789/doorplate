package com.peitu.doorplateqrcode.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Rising
 * @date 2019/6/21
 */
@Data
public class SlidingBlockData {

    public String comp;
    public String compMin;
    public int rx;

    public SlidingBlockData(String comp, String compMin, int rx) {
        this.comp = comp;
        this.compMin = compMin;
        this.rx = rx;
    }

    public SlidingBlockData() {

    }
}
