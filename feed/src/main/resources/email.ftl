<html>
    <head>
        <title>recent PubMed articles</title>
    </head>
    <body>
    <#list pubmedAbtractRecordList as pubmedAbtractRecord>

    <p><strong>${pubmedAbtractRecord.createDate?date},&nbsp;<em>${pubmedAbtractRecord.journal}

    </em></strong><em><span style="color: #39919e;">${pubmedAbtractRecord.title}</span>

    </em><strong>${(pubmedAbtractRecord.forename)!"[missing first name]"}&nbsp;${(pubmedAbtractRecord.lastname)!"[missing last name]"}</strong>

    ${(pubmedAbtractRecord.abstractText)!"Missing abstract."}

        Access the full study&nbsp;<span style="color: #39919e;"><a style="color: #39919e;" href="https://www.ncbi.nlm.nih.gov/pubmed/${pubmedAbtractRecord.pmid?c}">here</a></span>.</p>

    </#list>
    </body>
</html>


