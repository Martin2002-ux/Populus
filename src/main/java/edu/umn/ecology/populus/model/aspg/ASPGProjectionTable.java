/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.aspg;

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

/** TODO - Is this class even used??? */
public class ASPGProjectionTable extends AbstractTableModel{
	/**
	 *
	 */
	private static final long serialVersionUID = -5783523138285798405L;
	int dimension=0;
	int indexShift = -1;
	boolean lastCanHaveStarts = false;
	/*each element of the array refers to a column, each element in the vector
   refers to the row values*/
	Vector[] table;
	JTable jt;

	public ASPGProjectionTable() {
		table = new Vector[0];
		super.fireTableChanged(new TableModelEvent(this));
	}

	public void setTable(JTable t){
		jt = t;
	}

	void clearData(){
		table = new Vector[1];
		table[0] = new Vector();
		dimension = 0;
		super.fireTableChanged(new TableModelEvent(this));
	}

	public void addDimension(double initPop){
		dimension++;

		Vector[] temp = new Vector[dimension+1];
		for(int i=0; i<dimension-1; i++)
			temp[i] = table[i];
		temp[dimension] = table[dimension-1];
		temp[dimension-1] = new Vector(0);
		table = temp;

		table[dimension].add(new Double(initPop));

		for(int i=0; i<dimension-1;i++)
			table[dimension-1].add(new Double(0));
		for(int i=0; i<dimension; i++)
			table[i].add(new Double(0));
	}

	public void removeDimension(){
		dimension--;

		Vector[] temp = new Vector[dimension+1];
		for(int i=0; i<dimension; i++){
			temp[i] = table[i];
			temp[i].removeElementAt(dimension);
		}
		temp[dimension] = table[dimension+1];
		temp[dimension].removeElementAt(dimension);
		table = temp;

		if(!lastCanHaveStarts){
			setValueAt(new Double(0),0,dimension-1);
		}
		fireTableStructureChanged();
	}

	@Override
	public String getColumnName(int column) {
		return null;
	}

	@Override
	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(!lastCanHaveStarts && columnIndex == dimension-1) return false;
		if(rowIndex == 0 || columnIndex == dimension) return true;
		if(rowIndex-1 == columnIndex) return true;
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		table[columnIndex].setElementAt(aValue,rowIndex);
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return dimension;
	}
	@Override
	public int getColumnCount() {
		return dimension+1;
	}

	public int getDataDimension(){
		return dimension;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return table[column].get(row);
	}

	public void setNumRows(int newSize) {
		int oldSize = getRowCount();
		if ((newSize < 0) || (newSize == oldSize))
			return;
		if(newSize < oldSize){
			while(newSize != getRowCount()) removeDimension();
			fireTableStructureChanged();
			return;
		} else {
			while(newSize != getRowCount()) addDimension(0);
			fireTableStructureChanged();
			return;
		}
	}

	synchronized double[][] getMatrix() {
		double[][] data = new double[dimension][dimension];
		for(int i=0; i<data.length; i++)
			for(int j=0; j<data[i].length; j++)
				data[i][j] = ((Double)getValueAt(i,j)).doubleValue();
		return data;
	}

	synchronized double[] getPopulations(){
		double[] data = new double[dimension];
		for(int i=0; i<data.length; i++)
			data[i] = ((Double)getValueAt(i,dimension)).doubleValue();
		return data;
	}

	synchronized void setData(double[][] mat, double[] pops, int indexShift, boolean lastCanHaveStarts){
		clearData();
		this.lastCanHaveStarts = lastCanHaveStarts;
		if(mat.length != mat[0].length) return;
		if(mat.length > dimension){
			for(int i=dimension; i<mat.length; i++)
				this.addDimension(0);
		}
		for(int i=0; i<mat.length; i++)
			for(int j=0; j<mat.length; j++)
				this.setValueAt(new Double(mat[i][j]),i,j);

		Vector p = new Vector();
		for(int i=0; i<pops.length; i++)
			p.add(new Double(pops[i]));
		table[dimension] = p;

		fireTableStructureChanged();
	}

	public void setModel(boolean lastCanHaveStarts){
		this.lastCanHaveStarts = lastCanHaveStarts;
	}
}


