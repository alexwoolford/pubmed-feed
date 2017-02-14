package io.woolford.database.entity;


import java.util.Date;

public class PubMedAbstractRecord {

    private int pmid;
    private String title;
    private String journalTitle;
    private String docAbstract;
    private Date createDate;

    public int getPmid() {
        return pmid;
    }

    public void setPmid(int pmid) {
        this.pmid = pmid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getDocAbstract() {
        return docAbstract;
    }

    public void setDocAbstract(String docAbstract) {
        this.docAbstract = docAbstract;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "PubMedAbstractRecord{" +
                "pmid=" + pmid +
                ", title='" + title + '\'' +
                ", journalTitle='" + journalTitle + '\'' +
                ", docAbstract='" + docAbstract + '\'' +
                ", createDate=" + createDate +
                '}';
    }

}
