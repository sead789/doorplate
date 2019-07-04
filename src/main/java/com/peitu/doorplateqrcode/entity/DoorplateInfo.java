package com.peitu.doorplateqrcode.entity;

import lombok.Data;

/**
 * @author Rising
 * @date 2019/6/11
 */
@Data
public class DoorplateInfo {
    private Integer id;

    private String gmtCreate;

    private String gmtModified;

    private String administrativeCode;

    private String neighborhoodCommitteeTel;

    private String neighborhoodCommitteeAd;

    private String policeStation;

    private String policeStationTel;

    private String policeStationAd;

    private String doorplateName;

    private String doorplateNum;

    private String idcode;

    private String classes;

    private String state;

}