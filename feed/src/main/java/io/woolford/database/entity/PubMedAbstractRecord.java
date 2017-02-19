package io.woolford.database.entity;


import java.util.Date;

public class PubMedAbstractRecord {

    private int pmid;
    private String title;
    private String abstractText;
    private String journal;
    private int createYear;
    private int createMonth;
    private int createDay;
    private Date createDate;
    private String lastname;
    private String forename;
    private String initials;

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

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public int getCreateYear() {
        return createYear;
    }

    public void setCreateYear(int createYear) {
        this.createYear = createYear;
    }

    public int getCreateMonth() {
        return createMonth;
    }

    public void setCreateMonth(int createMonth) {
        this.createMonth = createMonth;
    }

    public int getCreateDay() {
        return createDay;
    }

    public void setCreateDay(int createDay) {
        this.createDay = createDay;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @Override
    public String toString() {
        return "PubMedAbstractRecord{" +
                "pmid=" + pmid +
                ", title='" + title + '\'' +
                ", abstractText='" + abstractText + '\'' +
                ", journal='" + journal + '\'' +
                ", createYear=" + createYear +
                ", createMonth=" + createMonth +
                ", createDay=" + createDay +
                ", createDate=" + createDate +
                ", lastname='" + lastname + '\'' +
                ", forename='" + forename + '\'' +
                ", initials='" + initials + '\'' +
                '}';
    }

}
