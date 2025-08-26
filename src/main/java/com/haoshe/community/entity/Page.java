package com.haoshe.community.entity;

// Encapsulate pagination-related information.
public class Page {
    /**
     * Represents the current page number for pagination.
     * The default value is 1, indicating the first page.
     */
    private int current = 1;

    /**
     * Represents the maximum number of items (e.g., posts) to display per page.
     * The default value is 10. This is the 'limit' used in database queries.
     */
    private int limit = 10;

    /**
     * Represents the total number of records (e.g., posts) that match the query.
     * This value is essential for calculating the total number of pages and the display range.
     */
    private int rows;

    /**
     * Stores the base path of the query.
     * This is used to construct reusable pagination links, ensuring that any existing
     * query parameters (e.g., search terms, filters) are preserved when navigating between pages.
     */
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= 1) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Calculates the starting row number for the current page.
     *
     * @return The offset, or the index of the first row to be retrieved from the database.
     */
    public int getOffset() {
        // The calculation is (current page number - 1) * number of items per page.
        // For example, if you're on page 3 with a limit of 10, the offset would be (3 - 1) * 10 = 20.
        // This means you skip the first 20 rows and start retrieving from the 21st row.
        return (current - 1) * limit;
    }

    /**
     * Calculates the total number of pages based on the total number of rows and the page size.
     *
     * @return The total number of pages required to display all rows.
     */
    public int getTotal(){
        // Check if the total number of rows is perfectly divisible by the page size.
        if(rows % limit == 0){
            // If there's no remainder, the total pages is simply rows / limit.
            return rows / limit;
        }else{
            // If there's a remainder, it means an extra page is needed for the remaining rows.
            return rows / limit + 1;
        }
    }

    /**
     * Calculates the starting page number for the pagination display.
     * This method ensures that the display range is reasonable (e.g., current page - 2).
     *
     * @return The first page number to be displayed in the pagination bar, ensuring it's not less than 1.
     */
    public int getFrom(){
        int from = current - 2;
        // If the calculated start page is less than 1, return 1 instead.
        return from < 1 ? 1 : from;
    }

    /**
     * Calculates the ending page number for the pagination display.
     * This method ensures the display range is reasonable (e.g., current page + 2).
     *
     * @return The last page number to be displayed in the pagination bar, ensuring it does not exceed the total number of pages.
     */
    public int getTo(){
        int to = current + 2;
        // First, get the total number of pages.
        int total = getTotal();
        // If the calculated end page exceeds the total number of pages, return the total instead.
        return to > total ? total : to;
    }
}