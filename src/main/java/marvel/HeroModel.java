package marvel;

import com.opencsv.bean.CsvBindByName;

/**
 * This is the Java representation of a Marvel Hero and the book the hero appeared in
 */
public class HeroModel {

    /**
     * name of the hero
     */
    @CsvBindByName
    private String hero;

    /**
     * books hero appears in
     */
    @CsvBindByName
    private String book;

    /**
     * get the name of the hero
     *
     * @return name of the hero
     */
    public String getHero() {
        return hero;
    }

    /**
     * set the name of the hero
     *
     * @param name name of the hero
     */
    public void setHero(String name) {
        this.hero = name;
    }

    /**
     * get the book that the hero appears in
     *
     * @return book that hero appears in
     */
    public String getBook() {
        return book;
    }

    /**
     * set the book of the hero
     *
     * @param book book that hero appears in
     */
    public void setBook(String book) {
        this.book = book;
    }
}