package com.salmonllc.swing.table;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;

import com.salmonllc.swing.SCheckBox;
import com.salmonllc.swing.SComponentHelper;

    public class CheckBoxCellEditor extends DefaultCellEditor {
        SCheckBox _cbx;

        public CheckBoxCellEditor(SCheckBox checkBox) {
            super(checkBox);
            _cbx = checkBox;
        }

        public Object getCellEditorValue() {
            return _cbx.isSelected() ? _cbx.getTrueValue() : _cbx.getFalseValue();
        }
        
        
		/**
		 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(JTable, Object, boolean, int, int)
		 */
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			SCheckBox box = (SCheckBox) super.getTableCellEditorComponent(table, value, isSelected, row, column);

		    boolean selected = false;
            if (SComponentHelper.valuesEqual(value,box.getTrueValue()))
                 selected = true;
            box.setSelected(selected);
			return box;
		}


    }
