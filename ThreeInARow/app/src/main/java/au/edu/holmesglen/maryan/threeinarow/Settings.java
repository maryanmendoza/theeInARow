package au.edu.holmesglen.maryan.threeinarow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class Settings extends AppCompatActivity {

    private Button applyChanges;
    public static final String MySETTINGS="MySettings";
    public static final String ColorOption="colorOption";
    public static final String ColorOption2="colorOption2";
    public static final String GRIDFOURBYFOUR="fourByfour";
    public static final String GRIDFIVEBYFIVE="fiveByFive";
    public static final String EASYLEVEL="sixtySeconds";
    public static final String DIFFICULTLEVEL="thirtySeconds";
    private RadioGroup rgColors;
    private RadioGroup rgGrids;
    private RadioGroup rgDifficulty;
    static int optionID;
    static int gridID;
    static int difficultyID;

    static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sharedPreferences=getSharedPreferences(MySETTINGS, Context.MODE_PRIVATE);
        applyChanges=(Button)findViewById(R.id.apply_changes);
        //this button on click saves the configuration that the user has given to the game
        applyChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveValue();

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
//this method saves the color, gridsize and difficulty level selected by the user
    public void saveValue()
    {
        rgGrids=(RadioGroup)findViewById(R.id.radioGroupGrids);
        rgColors=(RadioGroup) findViewById(R.id.radioGroupColors);
        rgDifficulty=(RadioGroup)findViewById(R.id.radioDifficultyLevel);
        difficultyID=rgDifficulty.getCheckedRadioButtonId();
        gridID=rgGrids.getCheckedRadioButtonId();
        optionID=rgColors.getCheckedRadioButtonId();

        if(optionID==R.id.radio_option1)
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt(ColorOption,optionID);
            editor.commit();
            Log.i("OPTION","option1 saved");
            //Toast.makeText(Settings.this,"option1saved "+optionID,Toast.LENGTH_SHORT).show();


        }
        if(optionID==R.id.radio_option2)
        {

            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt(ColorOption2,optionID);
            editor.commit();
            Log.i("OPTION","option2 saved");
            //Toast.makeText(Settings.this,"option2 saved"+optionID,Toast.LENGTH_SHORT).show();


        }
       if(gridID==R.id.gridFourByFour)
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt(GRIDFOURBYFOUR,gridID);
            editor.commit();
            Log.i("GRID","grid1 saved"+gridID);
            //Toast.makeText(Settings.this,"grid1saved"+gridID,Toast.LENGTH_SHORT).show();

        }
        if(gridID==R.id.gridFiveByFive)
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt(GRIDFIVEBYFIVE,gridID);
            editor.commit();
            Log.i("GRID","grid2 saved"+gridID);
            //Toast.makeText(Settings.this,"grid1saved"+gridID,Toast.LENGTH_SHORT).show();

        }
        if(difficultyID==R.id.radEasy)
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt(EASYLEVEL,difficultyID);
            editor.commit();
            Log.i("Easy","levelE saved"+difficultyID);
            //Toast.makeText(Settings.this,"level saved"+gridID,Toast.LENGTH_SHORT).show();

        }
        if(difficultyID==R.id.radDificult)
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt(DIFFICULTLEVEL,difficultyID);
            editor.commit();
            Log.i("Difficult","levelD saved"+difficultyID);
            //Toast.makeText(Settings.this,"level saved"+difficultyID,Toast.LENGTH_SHORT).show();
        }

    }
    // this method gets the color option  saved
    public static int getValues()
    {
        int color=0;
        if(optionID==R.id.radio_option1)
        {
            if(sharedPreferences.contains(ColorOption))
            {
                color=sharedPreferences.getInt(ColorOption,100);

            }
        }
        if(optionID==R.id.radio_option2)
        {
            if(sharedPreferences.contains(ColorOption))
            {
                color=sharedPreferences.getInt(ColorOption2,100);
            }

        }

        return color;
    }
 // this method gets the gridOption  saved
    public static int getGridOption()
    {
        int gridOption=0;
        if(gridID==R.id.gridFourByFour)
        {
            if(sharedPreferences.contains(GRIDFOURBYFOUR))
            {
                gridOption=sharedPreferences.getInt(GRIDFOURBYFOUR,100);


            }
        }
        if(gridID==R.id.gridFiveByFive)
        {
            if(sharedPreferences.contains(GRIDFIVEBYFIVE))
            {
                gridOption=sharedPreferences.getInt(GRIDFIVEBYFIVE,100);
            }
        }
        return gridOption;
    }

    //this method gets the difficulty level saved
    public static int getLevel()
    {
        int level=0;
        if(difficultyID==R.id.radEasy)
        {
            if(sharedPreferences.contains(EASYLEVEL))
            {
                level=sharedPreferences.getInt(EASYLEVEL,100);


            }
        }
        if(difficultyID==R.id.radDificult)
        {
            if(sharedPreferences.contains(DIFFICULTLEVEL))
            {
                level=sharedPreferences.getInt(DIFFICULTLEVEL,100);
            }
        }
        return level;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent i;
        switch(item.getItemId())
        {
            case R.id.content_help_menu:
                i= new Intent(this, HelpMenu.class);
                startActivity(i);
                return true;
            case R.id.content_three_in_arow:
                i= new Intent(this, ThreeInARow.class);
                startActivity(i);
                return true;

        }

        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.content_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
