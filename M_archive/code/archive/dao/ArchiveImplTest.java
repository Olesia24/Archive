package archive.dao;

import archive.model.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArchiveImplTest {
    Archive archive;
    LocalDateTime now = LocalDateTime.now();
    Document[] doc;



    @BeforeEach
    void setUp() {
        archive = new ArchiveImpl(5);
        doc = new Document[4];
        doc[0] = new Document(1,11, "Name1", "url1", now.minusDays(1));
        doc[1] = new Document(2,22, "Name2", "url2", now.minusDays(2));
        doc[2] = new Document(3,33, "Name3", "url3", now.minusDays(3));
        doc[3] = new Document(3,44, "Name4", "url4", now.minusDays(4));
        for (int i = 0; i <doc.length ; i++) {
            archive.addDocument(doc[i]);
        }
    }

    @Test
    void addDocumentTest() {
        assertFalse(archive.addDocument(null));
        assertFalse(archive.addDocument(doc[1]));
        Document document = new Document(5,55,"Name5", "url5", now);
        assertTrue(archive.addDocument(document));
        assertEquals(5, archive.size());
        Document document1 = new Document(6, 66, "Name6", "url6", now);
        assertFalse(archive.addDocument(document1));
    }

    @Test
    void updateDocumentTest() {
        assertTrue(archive.updateDocument(1, 11, "newUrl"));
        assertEquals("newUrl", archive.getDocumentFromFolder(1, 11).getUrl());
    }

    @Test
    void getDocumentFromFolderTest() {
        assertEquals(doc[0], archive.getDocumentFromFolder(1,11));
        assertNull(archive.getDocumentFromFolder(1, 33));
    }

    @Test
    void getAllDocumentsFromFolderTest() {
        Document[] expected = {doc[2], doc[3]};
        Document[] actual = archive.getAllDocumentsFromFolder(3);
        Arrays.sort(actual);
    }

    @Test
    void getDocumentsBetweenDateTest() {
        LocalDate ld = now.toLocalDate();
        Document[] actual = archive.getDocumentsBetweenDate(ld.minusDays(3), ld.minusDays(1));
        Arrays.sort(actual);
        Document[] expected = {doc[0], doc[1], doc[2]};
        assertArrayEquals(actual, expected);
    }

    @Test
    void size() {
        assertEquals(4, archive.size());
    }
    @Test
    void viewDocumentsTest() {
        archive.viewArchive(); // до сортировки
        Arrays.sort(doc);
        archive.viewArchive(); // после сортировки
    }
}