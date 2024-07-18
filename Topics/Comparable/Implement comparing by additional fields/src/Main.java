class Article implements Comparable<Article> {
    private String title;
    private int size;

    public Article(String title, int size) {
        this.title = title;
        this.size = size;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public int compareTo(Article otherArticle) {
        if (otherArticle.getSize() > this.getSize()) {
            return -1;
        } else if (otherArticle.getSize() < this.getSize()) {
            return 1;
        } else {
            if (otherArticle.getTitle().compareTo(getTitle()) > 0) {
                return -1;
            } else if (otherArticle.getTitle().compareTo(getTitle()) < 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}