/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mmio.lab;

import edu.function.Function8;
import edu.function.IFunction;
import edu.solution.DichotomySolution;
import edu.solution.FibonacciSolution;
import edu.solution.NoEquationException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MMIOLabActivity extends Activity {
	
	IFunction func;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.activity_main);
        
        Resources res = getResources();
        leftInterval = Double.parseDouble(res.getString(R.string.leftInterval_default_value));
        rightInterval = Double.parseDouble(res.getString(R.string.rightInterval_default_value));
        error = Double.parseDouble(res.getString(R.string.error_default_value));
        
        func = new Function8();
        ((GraphView)findViewById(R.id.graphView1)).addFunc(func);
        
        ((TextView)findViewById(R.id.textView1)).setText("Result: \n x: \n y: \n type: min");
    }

	@Override	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.activity_main, menu);
	    
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
	    switch (item.getItemId()) {
	        case R.id.method:
	        	showDialog(METHODS_DIALOG);
	            return true;
	        case R.id.error:
		        showDialog(ERROR_DIALOG);
	            return true;
	        case R.id.interval:
	        	showDialog(INTERVAL_DIALOG);
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	int method = R.string.dichotomy;
	Double leftInterval, rightInterval, error;

	@Override
	protected void onStart()
	{	
		switch (method)
			{
			case R.string.dichotomy:
				DichotomySolution solD = new DichotomySolution();
				try
					{
						solD.solve(func, leftInterval, rightInterval, error);
					} 
				catch (NoEquationException e)
					{
						Toast.makeText(MMIOLabActivity.this, R.string.no_solution_exception, Toast.LENGTH_LONG).show();
						
						e.printStackTrace();
					}
				break;
			case R.string.fibonacci:
				FibonacciSolution solF = new FibonacciSolution();
				try
					{
						solF.solve(func, leftInterval, rightInterval, error);
					} 
				catch (NoEquationException e)
					{
						Toast.makeText(MMIOLabActivity.this, R.string.no_solution_exception, Toast.LENGTH_LONG).show();
						
						e.printStackTrace();
					}
				break;
			default:
				break;
			}
		super.onStart();
	}

	private final int INTERVAL_DIALOG = 1;
	private final int METHODS_DIALOG = 2;
	private final int ERROR_DIALOG = 3;
	
	@Override
	protected Dialog onCreateDialog(int id)
	{
		switch (id)
			{
			case INTERVAL_DIALOG:
				LayoutInflater factory = LayoutInflater.from(this);
			    final View textEntryView = factory.inflate(R.layout.activity_interval, null);
			    return new AlertDialog.Builder(MMIOLabActivity.this)
			        .setTitle(R.string.menu_interval)
			        .setView(textEntryView)
			        .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int whichButton) {
			            	EditText leftIntervalEditbox = (EditText) ((Dialog)dialog).findViewById(R.id.leftInterval_edit);
			                leftInterval = Double.parseDouble(leftIntervalEditbox.getText().toString());
			                
			                EditText rightIntervalEditbox = (EditText) ((Dialog)dialog).findViewById(R.id.rightInterval_edit);
			                rightInterval = Double.parseDouble(rightIntervalEditbox.getText().toString());
			            }
			        })
			        .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int whichButton) {
			            	EditText leftIntervalEditbox = (EditText) ((Dialog)dialog).findViewById(R.id.leftInterval_edit);
			                leftIntervalEditbox.setText(leftInterval.toString());
			                
			                EditText rightIntervalEditbox = (EditText) ((Dialog)dialog).findViewById(R.id.rightInterval_edit);
			                rightIntervalEditbox.setText(rightInterval.toString());
			                dialog.dismiss();
			            }
			        })
			        .create();
			case ERROR_DIALOG:
				LayoutInflater factoryError = LayoutInflater.from(this);
			    final View textEntryViewError = factoryError.inflate(R.layout.activity_error, null);
			    return new AlertDialog.Builder(MMIOLabActivity.this)
			        .setTitle(R.string.menu_error)
			        .setView(textEntryViewError)
			        .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int whichButton) {
			            	EditText ErrorEditbox = (EditText) ((Dialog)dialog).findViewById(R.id.error_edit);
			                error = Double.parseDouble(ErrorEditbox.getText().toString());
			            }
			        })
			        .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int whichButton) {
			            	EditText ErrorEditbox = (EditText) ((Dialog)dialog).findViewById(R.id.error_edit);
			            	ErrorEditbox.setText(error.toString());
			                dialog.dismiss();
			            }
			        })
			        .create();
			case METHODS_DIALOG:
				return new AlertDialog.Builder(MMIOLabActivity.this)
		        .setTitle(R.string.menu_method)
		        .setSingleChoiceItems(R.array.Methods, 0, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
		            	switch (whichButton)
						{
						case 0:
							method = R.string.dichotomy;
							break;
						case 1:
							method = R.string.fibonacci;
							break;
						default:
							break;
						}
		            	dialog.cancel();
		            }
		        })
		       .create();
			default:
				return null;
			}
	}
    
}