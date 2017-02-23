package io.woolford.database.entity;


public class PmidEmailRecord {

    private int pmid;
    private String email;

    public int getPmid() {
        return pmid;
    }

    public void setPmid(int pmid) {
        this.pmid = pmid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PmidEmailRecord{" +
                "pmid=" + pmid +
                ", email='" + email + '\'' +
                '}';
    }

}
