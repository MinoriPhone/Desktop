package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class MediaItemTableModel extends AbstractTableModel {

    // Variables
    private ArrayList<MediaItem> mediaItemList;
    private String[] headerList = {"Absolute path", "Filename", "Duration"};
    private Class[] classes = {String.class, String.class, Integer.class};

    /**
     * Constructor
     */
    public MediaItemTableModel() {
        mediaItemList = new ArrayList<MediaItem>();
    }

    /**
     * Returns the number of columns in the model
     *
     * @return int
     */
    @Override
    public int getColumnCount() {
        return headerList.length;
    }

    /**
     * Returns the number of rows in the model
     *
     * @return int
     */
    @Override
    public int getRowCount() {
        return mediaItemList.size();
    }

    /**
     * Returns the most specific superclass for all the cell values in the column
     *
     * @param arg int The index of the column
     *
     * @return Class<?> The common ancestor class of the object values in the model
     */
    @Override
    public Class<?> getColumnClass(int arg) {
        return classes[arg];
    }

    /**
     * Returns the name of the column at columnIndex
     *
     * @param col int The index of the column
     *
     * @return String The name of the column
     */
    @Override
    public String getColumnName(int col) {
        return headerList[col];
    }

    /**
     * Returns the value for the cell at columnIndex and rowIndex
     *
     * @param row int The row whose value is to be queried
     * @param column int The column whose value is to be queried
     *
     * @return Object The value Object at the specified cell
     */
    @Override
    public Object getValueAt(int row, int column) {
        MediaItem item = null;
        item = mediaItemList.get(row);
        switch (column) {
            case 0:
                return item.getAbsolutePath();
            case 1:
                return item.getFileName();
            case 2:
                return item.getShowDurationInSeconds();
            default:
                return "";
        }
    }

    /**
     * Sets the value in the cell at columnIndex and rowIndex to a parameter value
     *
     * @param value Object The new value
     *
     * @param row int The row whose value is to be changed
     * @param col int The column whose value is to be changed
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        MediaItem item = null;
        item = mediaItemList.get(row);
        switch (col) {
            case 0:
                item.setAbsolutePath((String) value);
                break;
            case 1:
                item.setFileName((String) value);
                break;
            case 2:
                item.setShowDurationInSeconds((Integer) value);
                break;
            default:
                break;
        }

        // Notifies all listeners that the value of the cell at [row, column] has been updated
        fireTableCellUpdated(row, col);
    }

    /**
     * Get the object MediaItem by a given row
     *
     * @param row int The index of the row
     *
     * @return MediaItem
     */
    public MediaItem getRow(int row) {
        return mediaItemList.get(row);
    }

    /**
     * Remove a row from the table by the given MediaItem
     *
     * @param row MediaItem
     */
    public void removeRow(MediaItem row) {
        mediaItemList.remove(row);
        this.fireTableDataChanged();
    }

    /**
     * Add a row to the table with the given MediaItem
     *
     * @param row MediaItem
     */
    public void addRow(MediaItem row) {
        mediaItemList.add(row);
        this.fireTableDataChanged();
    }
}
