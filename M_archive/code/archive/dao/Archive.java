package archive.dao;

import archive.model.Document;

import java.time.LocalDate;

public interface Archive {
    boolean addDocument(Document document);
    Document removeDocument(int idDocument);
    boolean updateDocument(int idFolder, int idDocument, String url);
    Document findDocument(int idDocument);
    Document getDocumentFromFolder(int idFolder, int idDocument);
    Document[] getAllDocumentsFromFolder(int idFolder);
    Document[] getDocumentsBetweenDate(LocalDate dateFrom, LocalDate dateTo);
    int size();
    void viewArchive(); //метод для печати содержимого
    void exit();
}
