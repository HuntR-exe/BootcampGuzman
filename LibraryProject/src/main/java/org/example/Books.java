package org.example;

public class Books {
    private int bookID;
    private String bookISBN;
    private String boolTitle;
    private boolean isCheckedOut;
    private String checkedOutTo;

    public Books(int bookID, String bookISBN, String boolTitle, boolean isCheckedOut, String checkedOutTo) {
        this.bookID = bookID;
        this.bookISBN = bookISBN;
        this.boolTitle = boolTitle;
        this.isCheckedOut = isCheckedOut;
        this.checkedOutTo = checkedOutTo;
    }

    public Books(int bookID, String bookISBN, String boolTitle, boolean isCheckedOut) {
        this.bookID = bookID;
        this.bookISBN = bookISBN;
        this.boolTitle = boolTitle;
        this.isCheckedOut = isCheckedOut;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getBoolTitle() {
        return boolTitle;
    }

    public void setBoolTitle(String boolTitle) {
        this.boolTitle = boolTitle;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;
    }
}
