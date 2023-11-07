package archive;

import archive.dao.Archive;
import archive.dao.ArchiveImpl;
import archive.model.Document;
import archive.model.Menu;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ArchiveAppl {
    public static void main(String[] args) {
        // greeting
        System.out.println("Welcome to Archive.");
        Archive archive = new ArchiveImpl(100);

        // начало цикла
        while (true) {
            // print menu
            Menu.printMenu(); // статический метод вызывается по имени класса
            // ask choice
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input your choice: ");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1: { //add document
                        scanner.nextLine();
                        System.out.println("Input folder ID: ");
                        int folderId = scanner.nextInt();
                        System.out.println("Input document ID: ");
                        int documentId = scanner.nextInt();
                        scanner.nextLine(); //переход на новую строку и ожидание ввода
                        System.out.println("Input document title: ");
                        String title = scanner.nextLine();
                        System.out.println("Input document url: ");
                        String url = scanner.nextLine();
                        LocalDateTime currTime = LocalDateTime.now();//
                        Document document = new Document(folderId, documentId, title, url, currTime);
                        archive.addDocument(document);
                        break;
                    }
                    case 2: { //view documents
                        System.out.println("List of documents in Archive: ");
                        archive.viewArchive();
                        break;
                    }
                    case 3: { //find documents
                        System.out.println("Enter ID Document: ");
                        int number = scanner.nextInt();
                        System.out.println(archive.findDocument(number));
                        break;
                    }
                    case 4: {//Delete document
                        System.out.println("Enter ID Document to remove:");
                        int number = scanner.nextInt();
                        archive.removeDocument(number);
                        archive.viewArchive();
                        break;
                    }
                    case 5: {//exit
                        archive.exit();
                        break;
                    }
                    default: {
                        System.out.println("Wrong input.");
                        archive.exit();
                    }
                }
            } catch (Exception e){
                System.err.println("Something went wrong :-/");
                e.printStackTrace();
            }
        }
    }
}

