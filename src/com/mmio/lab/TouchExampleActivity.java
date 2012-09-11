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
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class TouchExampleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        /*GraphView view = new GraphView(this);
        view.addFunc(new Function8());
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));*/
        
        setContentView(R.layout.activity_main);//setContentView(view);
        ((GraphView)findViewById(R.id.graphView1)).addFunc(new Function8());
        
        ((TextView)findViewById(R.id.textView1)).setText("Result: \n x: \n y: \n type: min");
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
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
	        case R.id.preferences:
	        	Intent settingsActivity = new Intent(getBaseContext(), Preference.class);
	        	startActivity(settingsActivity);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	String variant, method;
	
	private void getPrefs() {
         // Get the xml/pref.xml preferences
         SharedPreferences prefs = PreferenceManager
                         .getDefaultSharedPreferences(getBaseContext());
         
         variant = prefs.getString("Variant", "1");
         method = prefs.getString("Method", "1");
 }

	@Override
	protected void onStart()
	{
		getPrefs();
		super.onStart();
	}
    
}