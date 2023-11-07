package archive.model;

public enum Menu {
        ADD(1, "Add document"), VIEW(2, "View of documents"), FIND(3, "Find document"),DELETE(4, "Delete document"), EXIT(5, "Exit");
        //fields
        private int menuItem;
        private String action;
        // constructor
        Menu(int menuItem, String action) {
            this.menuItem = menuItem;
            this.action = action;
        }
        // метод, void - ничего не возвращает, он печатает
        public static void printMenu(){
            Menu[] menu = Menu.values(); // put enum items into array
            for (int i = 0; i < menu.length; i++) {
                System.out.print((menu[i].menuItem) + " - " + menu[i].action + " | ");
            }
            System.out.println(); // new line
        }
    }

