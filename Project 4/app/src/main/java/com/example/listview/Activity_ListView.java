package com.example.listview;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Activity_ListView extends AppCompatActivity {


	public static ListView my_listview;
	public static ArrayList<BikeData> bikeList;
	public static SharedPreferences myPrefs;
	private static SharedPreferences.OnSharedPreferenceChangeListener listener;
	public static String URL;
	private CustomAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Change title to indicate sort by
		setTitle("Sort by:");
		URL = "";
		//listview that you will operate on
		my_listview = (ListView)findViewById(R.id.lv);
		//toolbar
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		android.support.v7.app.ActionBar actionBar = getSupportActionBar();
		//preferences
		myPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		//checks to see what website to use with preferences
		listener = new SharedPreferences.OnSharedPreferenceChangeListener(){
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
				if (key.equals("INFO_KEY")){
					DownloadTask myTask = new DownloadTask(Activity_ListView.this);
					myTask.execute(sharedPreferences.getString(key,""));
					URL = sharedPreferences.getString(key,"");
				}
			}
		};
		myPrefs.registerOnSharedPreferenceChangeListener(listener);
		//initializes bikeList
		bikeList = new ArrayList<BikeData>();
		//checks to see if connection is there, starts download task
		if (ConnectivityCheck.isNetworkReachableAlertUserIfNot(this)){
			DownloadTask myTask = new DownloadTask(this);
			myTask.execute(myPrefs.getString("INFO_KEY", ""));
			URL = myPrefs.getString("INFO_KEY","");
		}
		//sets up spinner
		setupSimpleSpinner();
		//set the listview onclick listener
		setupListViewOnClickListener();

	}

	private void setupListViewOnClickListener() {
		my_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				AlertDialog.Builder alert = new AlertDialog.Builder(Activity_ListView.this);
				alert.setMessage(bikeList.get(position).toString());
				alert.show();
			}
		});
	}

	protected void createAdapter(String JSONString){
		System.out.println(JSONString);
		bikeList = JSONHelper.parseAll(JSONString);
		adapter = new CustomAdapter(this);
		my_listview.setAdapter(adapter);
	}

	Spinner spinner;
	public void setupSimpleSpinner() {
		ArrayList<String> adapterList = new ArrayList<String>();
		adapterList.add("Company");
		adapterList.add("Model");
		adapterList.add("Price");
		adapterList.add("Location");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_item,adapterList);
		spinner = (Spinner)findViewById(R.id.spinner);
		spinner.setAdapter(adapter);


		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public static final int SELECTED_ITEM = 0;

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (parent.getChildAt(SELECTED_ITEM) != null) {
					sortList(position);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

	}

	private void sortList(int position) {
		switch (position){
			//company
			case 0:
				Collections.sort(bikeList,new ComparatorCompany());
				if (adapter != null){
					adapter.notifyDataSetChanged();
				}
				break;
			//model
			case 1:
				Collections.sort(bikeList,new ComparatorModel());
				adapter.notifyDataSetChanged();
				break;
			//price
			case 2:
				Collections.sort(bikeList,new ComparePrice());
				adapter.notifyDataSetChanged();
				break;
			//location
			case 3:
				Collections.sort(bikeList,new CompareLocation());
				adapter.notifyDataSetChanged();
				break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.action_settings:
				Intent myIntent = new Intent(this,SettingsActivity.class);
				startActivity(myIntent);
				break;
			case R.id.refresh:
				DownloadTask myTask = new DownloadTask(this);
				setupSimpleSpinner();
				myTask.execute(myPrefs.getString("INFO_KEY", ""));
				URL = myPrefs.getString("INFO_KEY","");
				break;

		}
		return true;
	}

}
