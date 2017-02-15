package io.woolford.database.entity;

public class DoctorRecord {

    private String doctorName;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public String toString() {
        return "DoctorRecord{" +
                "doctorName='" + doctorName + '\'' +
                '}';
    }

}
