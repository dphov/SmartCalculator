class Book {

    private String title;
    private int yearOfPublishing;
    private String[] authors;  
    String getTitle() {
        return this.title;
    }
    void setTitle(String t) {
        this.title = t;
    }
    int getYearOfPublishing() {
        return this.yearOfPublishing;
    }
    void setYearOfPublishing(int y) {
        this.yearOfPublishing = y;
    }
    String[] getAuthors() {
        return this.authors;
    }
    void setAuthors(String[] a) {
        this.authors = a;
    }
    
}