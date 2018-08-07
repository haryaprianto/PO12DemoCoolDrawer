package com.example.a16022596.po12democooldrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private String[] drawerItems;
   private android.support.v4.widget.DrawerLayout drawerLayout;
   private android.widget.ListView drawerList;
   android.widget.ArrayAdapter<String> aa;
   String currentTitle;
   android.support.v7.app.ActionBar ab;

   private android.support.v7.app.ActionBarDrawerToggle drawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            currentTitle = this.getTitle().toString();

	drawerLayout = (android.support.v4.widget.DrawerLayout) findViewById(R.id.drawer_layout);
	drawerList = (android.widget.ListView) findViewById(R.id.left_drawer);

	drawerItems = new String[] { "Bio", "Vaccination", "Anniversary" };
	ab = getSupportActionBar();

	aa = new android.widget.ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_activated_1, drawerItems);
	drawerList.setAdapter(aa);

	// Set the list's click listener
	drawerList.setOnItemClickListener(new
                           android.widget.AdapterView.OnItemClickListener(){
	   @Override
	   public void onItemClick(android.widget.AdapterView<?> arg0, android.view.View arg1, int
			position, long arg3) {

		android.support.v4.app.Fragment fragment = null;
		if (position == 0)
			fragment = new BioFragment();
		else if (position == 1)
			fragment = new VaccinationFragment();
		else if (position == 2)
			fragment = new AnniversaryFragment();

		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
             android.support.v4.app.FragmentTransaction trans = fm.beginTransaction();
             trans.replace(R.id.content_frame, fragment);
             trans.commit();
             drawerList.setItemChecked(position, true);
		currentTitle = drawerItems[position];
		ab.setTitle(currentTitle);
		drawerLayout.closeDrawer(drawerList);
	   }
	});


    drawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,
    	drawerLayout, 	  /* DrawerLayout object */
      R.string.drawer_open, /* "open drawer" description */
      R.string.drawer_close /* "close drawer" description */
    ) {

      /** Would be called when a drawer has completely closed */
      @Override
      public void onDrawerClosed(android.view.View view) {
        super.onDrawerClosed(view);
        ab.setTitle(currentTitle);
      }

      /** Would be called when a drawer has completely open */
      @Override
      public void onDrawerOpened(android.view.View drawerView) {
        super.onDrawerOpened(drawerView);
        ab.setTitle("Make a selection");
      }
    };

    // Set the drawer toggle as the DrawerListener
drawerLayout.addDrawerListener(drawerToggle);
    ab.setDisplayHomeAsUpEnabled(true);
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync toggle state so the indicator is shown properly.
        //  Have to call in onPostCreate()
        drawerToggle.syncState();
  }

  @Override
  public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
  }

  @Override
  public boolean onOptionsItemSelected(android.view.MenuItem item) {
      // The home/up action should open or close the drawer.
      // ActionBarDrawerToggle will take care of this.
      if (drawerToggle.onOptionsItemSelected(item))
        return true;

      return super.onOptionsItemSelected(item);
  }

    }

