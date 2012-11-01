package Model;

import javax.swing.table.DefaultTableModel;

public class CustomDefaultTableModel extends DefaultTableModel {

    /**
     * Just implement the normal getRowCount()
     *
     * @return super.getRowCount();
     */
    @Override
    public int getRowCount() {
        return super.getRowCount();
    }

    /**
     * Just implement the normal getColumnCount()
     *
     * @return super.getColumnCount();
     */
    @Override
    public int getColumnCount() {
        return super.getColumnCount();
    }

    /**
     * Just implement the normal getValueAt()
     *
     * @param row int
     * @param column int
     *
     * @return super.getValueAt(row, column);
     */
    @Override
    public Object getValueAt(int row, int column) {
        return super.getValueAt(row, column);
    }

    /**
     * Make cells non-editable
     *
     * @param row int
     * @param column int
     *
     * @return false
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
