package com.isi.appmon.bean;

/**
 * Created by samurdiw on 1/11/2017.
 */
public class DiskUsageBean {
    private String driveName;
    private double totalSpace;
    private double usableSpace;
    private double freeSpace;

    public String getDriveName() {
        return driveName;
    }

    public void setDriveName(String driveName) {
        this.driveName = driveName;
    }

    public double getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(double totalSpace) {
        this.totalSpace = totalSpace;
    }

    public double getUsableSpace() {
        return usableSpace;
    }

    public void setUsableSpace(double usableSpace) {
        this.usableSpace = usableSpace;
    }

    public double getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(double freeSpace) {
        this.freeSpace = freeSpace;
    }
}
