package io.woolford.database.entity;


public class PubMedAbstractRestRecord {

    private int pmid;
    private String title;
    private String abstractText;
    private String journal;
    private String createDate;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
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
                ", createDate='" + createDate + '\'' +
                ", lastname='" + lastname + '\'' +
                ", forename='" + forename + '\'' +
                ", initials='" + initials + '\'' +
                '}';
    }

}
