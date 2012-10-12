package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class MediaItemTableModel extends AbstractTableModel {

    // Variables
    private ArrayList<MediaItem> mediaItemList;
    private String[] headerList = {"Absolute path to file", "Filename and type", "Duration in seconds"};
    private Class[] classes = {String.class, String.class, Integer.class};

    /**
     * Constructor
     *
     * @param list ArrayList<MediaItem>
     */
    public MediaItemTableModel() {
        mediaItemList = new ArrayList<MediaItem>();
    }

    @Override
    public int getColumnCount() {
        return headerList.length;
    }

    @Override
    public int getRowCount() {
        return mediaItemList.size();
    }

    @Override
    public Class<?> getColumnClass(int arg) {
        return classes[arg];
    }

    @Override
    public String getColumnName(int col) {
        return headerList[col];
    }

    /**
     * Always return the MediaItem object when we click on a row (or a certain cell in that row)
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
     * Change a cell value
     *
     * @param value
     * @param row
     * @param col
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
        // Update cell
        fireTableCellUpdated(row, col);
    }

    public MediaItem getRow(int row) {
        return mediaItemList.get(row);
    }

    public void removeRow(MediaItem row) {
        mediaItemList.remove(row);
        this.fireTableDataChanged();
    }

    public void addRow(MediaItem row) {
        mediaItemList.add(row);
        this.fireTableDataChanged();
    }
}
