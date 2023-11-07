package archive.dao;

import archive.model.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;


public class ArchiveImpl implements Archive {
    //этот компаратор позволяем сортировать обьекты типа Document
    static Comparator<Document> comparator = (d1, d2)-> {
        int res = d1.getDate().compareTo(d2.getDate());
        return res != 0 ? res : Integer.compare(d1.getIdDocument(), d2.getIdDocument());
    };

    private Document[] documents; //масив который отвечет за хранение документа
    private int size; // количество документов

    //конструктор
    public ArchiveImpl(int capacity){
        documents = new Document[capacity];
    }

    @Override
    public boolean addDocument(Document document) {
        if (document == null || size == documents.length
                || getDocumentFromFolder(document.getIdFolder(), document.getIdDocument()) != null){
            return false;
        }
        int index = Arrays.binarySearch(documents, 0, size, document, comparator);
        index = index>=0 ? index : -index-1; //нашли индекс куда надо вставить документ в массив
        System.arraycopy(documents, index, documents, index+1, size-index);//раздвинули массив //length это длина хвоста
        documents[index] = document;//вставили документ
        size++;//увеличили размер хранилища
        return true;
    }

    @Override
    public Document removeDocument(int documentId) {
        for (int i = 0; i < size; i++) {
            if (documents[i].getIdDocument() == documentId) {
                Document temp = documents[i];
                documents[i] = documents[size - 1];
                documents[size - 1] = null;
                size--;
                return temp;
            }
        }
        return null;
    }

    @Override
    public boolean updateDocument(int idFolder, int idDocument, String url) {
        Document document = getDocumentFromFolder(idFolder, idDocument);
        if (document == null) {
            return false;
        }
        document.setUrl(url);//обновляем одно поле url
        return true;
    }

    @Override
    public Document findDocument(int idDocument) {
        for (int i = 0; i <size ; i++) {
            if (documents[i].getIdDocument()==idDocument){
                return documents[i];
            }

        }return null;
        //System.out.println(documents[i]);
    }

    @Override
    public Document getDocumentFromFolder(int idFolder, int idDocument) {
        Document pattern = new Document(idFolder, idDocument, null, null, null);
        for (int i = 0; i <size ; i++) {
            if (pattern.equals(documents[i])){
                return documents[i];
            }
        } return null;
    }

    @Override
    public Document[] getAllDocumentsFromFolder(int idFolder) {
        return findByPredicate(d-> d.getIdFolder() ==idFolder);//передаем в метод findByPredicat сам предикат(условие для поиска)
    }

    private Document[] findByPredicate(Predicate<Document>predicate) {//возвращаем массив найденных элементов
        Document[] res = new Document[size]; //обьявили массив избыточной длины
        int j = 0; //это счетчик найденых документов
        for (int i = 0; i <size ; i++) {
            if (predicate.test(documents[i])){
                res [j] = documents[i];
                j++;
            }
        } return Arrays.copyOf(res, j);//скопировали массив сам на себя, теперь он без элементов null
    }

    @Override
    public Document[] getDocumentsBetweenDate(LocalDate dateFrom, LocalDate dateTo) {
        Document pattern = new Document(0, Integer.MIN_VALUE, null, null, dateFrom.atStartOfDay());
        int from = -Arrays.binarySearch(documents, 0, size, pattern, comparator)-1;
        pattern = new Document(0, Integer.MAX_VALUE, null, null, LocalDateTime.of(dateTo, LocalTime.MAX));
        int to = -Arrays.binarySearch(documents, 0, size, pattern, comparator)-1;
        return Arrays.copyOfRange(documents, from, to);
        }

    @Override
    public int size() {
        return size;
    }

    public void viewArchive() {
        for (int i = 0; i < size; i++) {
            System.out.println(documents[i].getIdDocument()+ " - " + documents[i].getName()+ " / " + documents[i].getDate());
        }
        System.out.println("You have " + size + " documents.");
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}
