package archive.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Document implements Comparable<Document> {
    private int idFolder;
    private int idDocument;
    private String name;
    private String url;
    private LocalDateTime date;

    public Document(int idFolder, int idDocument, String name, String url, LocalDateTime date) {
        this.idFolder = idFolder;
        this.idDocument = idDocument;
        this.name = name;
        this.url = url;
        this.date = date;
    }

    public int getIdFolder() {
        return idFolder;
    }

    public int getIdDocument() {
        return idDocument;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "idFolder=" + idFolder +
                ", idDocument=" + idDocument +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document archive = (Document) o;
        return idFolder == archive.idFolder && idDocument == archive.idDocument;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFolder, idDocument);
    }

    @Override
    public int compareTo(Document o) {
        return this.idDocument -o.idDocument;
    }
}
