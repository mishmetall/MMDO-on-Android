package com.mmio.lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import edu.math.Round;



import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CompareTable extends Activity
{
	private HashMap<String,  ArrayList<Double>> comparTable = new HashMap<String, ArrayList<Double>>();
	private int maxRows = 1;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

        setContentView(R.layout.iteration_table);
        
        Bundle extras = getIntent().getExtras();
        addIterTable(extras.getString("DICHOTOMY_NAME"), (ArrayList<Double>)extras.getSerializable("DICHOTOMY_TABLE"));
        addIterTable(extras.getString("FIBONACCI_NAME"), (ArrayList<Double>)extras.getSerializable("FIBONACCI_TABLE"));
        composeTable((TableLayout)findViewById(R.id.table));
	}

	private void composeTable(TableLayout tableLayout)
	{
		/* Adding header of the table */
		TableRow header = new TableRow(this);
		
		/* First column is the number of iteration */
		TextView num = new TextView(this);
		num.setText("#");
		num.setPadding(10, 0, 10, 0);
		
		header.addView(num);
		
		ArrayList<TableRow> contentOfTable = new ArrayList<TableRow>();
		for (Integer i=1; i<=maxRows; i++)
			{
				TextView numRow = new TextView(this);
				numRow.setText(i.toString());
				numRow.setPadding(10, 0, 10, 0);
				
				TableRow row = new TableRow(this);
				row.addView(numRow);
				contentOfTable.add(row);
			}
		
		for (Entry<String, ArrayList<Double>> table : comparTable.entrySet())
			{
				TextView nameOfMethod = new TextView(this);
				nameOfMethod.setText(table.getKey());
				nameOfMethod.setPadding(10, 0, 10, 0);
				header.addView(nameOfMethod);
				
				int currRow = 0;
				for (Double valueOfFunction : table.getValue())
					{
						TextView value = new TextView(this);
						value.setText(Round.roundResult(valueOfFunction, 6).toString());
						
						contentOfTable.get(currRow).addView(value);
						currRow++;
					}
				while(currRow<maxRows)
					{
						TextView value = new TextView(this);
						contentOfTable.get(currRow).addView(value);
						
						currRow++;
					}
			}
		
		tableLayout.addView(header);
		for (TableRow tableRow : contentOfTable)
			{
				tableLayout.addView(tableRow);
			}
	}
	
	public void addIterTable(String nameOfMethod, ArrayList<Double> iterations)
	{
		comparTable.put(nameOfMethod, iterations);
		if (maxRows < iterations.size())
			maxRows = iterations.size();
	}
	
}
