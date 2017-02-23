package au.edu.holmesglen.maryan.threeinarow;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameScreen extends AppCompatActivity {

    int colorOrange=(R.drawable.orange);
    int colorBlue2=(R.drawable.blue2);
    int colorPurple=(R.drawable.purple);
    int colorBlue=(R.drawable.blue);
    int colorRed=(R.drawable.red);
    int colorWhite=(R.drawable.white);
    int click=0;
    int color1;
    int color2;
    int colorChoice;
    int gridChoice;
    int levelChoice;
    GridView gridView;
    Item[] gridArray;
    ImageAdapter iAdapter;
    private Button mResetGameButton;
    Random randomColor;
    private CountDownTimer mCountDownTimer;
    private TextView timer;
    public static final String MySCORES="scores";
    public static final String SCORE="score";
    static SharedPreferences scorePref;
    int counter=0;
    int chronometer;

    //This method gives the inicial configuration to the gridview
    //colors, size and assigns the four random color tiles to the greyed grid
    public void initialColor(){

        start();

        //this code is for selecting the size of the grid according to the user prefereces

        if((gridChoice=Settings.getGridOption())>0)
        {
            gridView=(GridView) findViewById(R.id.gridview);
            switch (gridChoice){
                //if the user selects a four by four
                case R.id.gridFourByFour:
                    gridView.setNumColumns(4);
                    gridArray= new Item[16];

                    for(int i=0;i<16;i++){
                        gridArray[i]=new Item(R.drawable.grey,"grey");
                    }

                    if((colorChoice= Settings.getValues())>0)
                    {
                        switch (colorChoice) {
                            case R.id.radio_option1:
                                color1 = R.drawable.orange;
                                color2 = R.drawable.blue2;
                                break;
                            case R.id.radio_option2:
                                color1 = R.drawable.purple;
                                color2 = R.drawable.blue;
                        }
                    }
                    else
                    {   color1=R.drawable.white;
                        color2=R.drawable.red;
                    }
                    int[] randomNumbers;
                    randomColor=new Random();
                    randomNumbers=new int[4];

                    counter=0;
                    //boolean flag=false;

                    while(counter<4 && counter>=0)
                    {
                        //int [] colors= new int[2];
                        //colors=colorPreferences();
                        int number;
                        boolean flag=false;
                        number = randomColor.nextInt(16);
                        for(int j = 0;j<4;j++)
                        {
                            if(number == randomNumbers[j])
                            {
                                flag=true;
                                // /Toast.makeText(getApplicationContext(),""+flag,Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                flag=false;
                                //Toast.makeText(getApplicationContext(),"identical",Toast.LENGTH_SHORT).show();
                            }
                        }
                        if(!flag)
                        {//Toast.makeText(getApplicationContext(),""+flag,Toast.LENGTH_SHORT).show();
                            randomNumbers[counter] = number;
                            counter++;
                        }
                        if(flag)
                        {//Toast.makeText(getApplicationContext(),"Flag",Toast.LENGTH_SHORT).show();
                            counter--;
                        }
                        Log.i("Counter"," "+counter);
                    }
                    gridArray[randomNumbers[0]].setColor(color1);
                    gridArray[randomNumbers[1]].setColor(color1);
                    gridArray[randomNumbers[2]].setColor(color2);
                    gridArray[randomNumbers[3]].setColor(color2);

                    break;
                //if the user selects the five by five grid
                case R.id.gridFiveByFive:

                    gridView.setNumColumns(5);
                    gridArray= new Item[25];
                    for(int i=0;i<25;i++){
                        gridArray[i]=new Item(R.drawable.grey,"grey");
                    }

                    if((colorChoice= Settings.getValues())>0)
                    {
                        switch (colorChoice) {
                            case R.id.radio_option1:
                                color1 = R.drawable.orange;
                                color2 = R.drawable.blue2;
                                break;
                            case R.id.radio_option2:
                                color1 = R.drawable.purple;
                                color2 = R.drawable.blue;
                        }
                    }
                    else
                    {   color1=R.drawable.white;
                        color2=R.drawable.red;
                    }


                    randomColor=new Random();
                    randomNumbers=new int[4];
                    counter=0;
                    //boolean flag=false;

                    while(counter<4)
                    {
                        //int [] colors= new int[2];
                        //colors=colorPreferences();
                        int number;
                        boolean flag=false;
                        number = randomColor.nextInt(16);
                        for(int j = 0;j<4;j++)
                        {
                            if(number == randomNumbers[j])
                            {
                                flag=true;
                                // /Toast.makeText(getApplicationContext(),""+flag,Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                flag=false;
                                //Toast.makeText(getApplicationContext(),"identical",Toast.LENGTH_SHORT).show();
                            }
                        }
                        if(!flag)
                        {//Toast.makeText(getApplicationContext(),""+flag,Toast.LENGTH_SHORT).show();
                            randomNumbers[counter] = number;
                            counter++;
                        }
                        if(flag)
                        {
                            //Toast.makeText(getApplicationContext(),"Flag",Toast.LENGTH_SHORT).show();
                            counter--;
                        }

                    }
                    gridArray[randomNumbers[0]].setColor(color1);
                    gridArray[randomNumbers[1]].setColor(color1);
                    gridArray[randomNumbers[2]].setColor(color2);
                    gridArray[randomNumbers[3]].setColor(color2);

                    break;
            }
        }

        else{
            //default values for the size of the grid
            gridView=(GridView) findViewById(R.id.gridview);
            gridView.setNumColumns(4);
            gridArray= new Item[16];

            for(int i=0;i<16;i++){
                gridArray[i]=new Item(R.drawable.grey,"grey");
            }

            if((colorChoice= Settings.getValues())>0)
            {
                switch (colorChoice) {
                    case R.id.radio_option1:
                        color1 = R.drawable.orange;
                        color2 = R.drawable.blue2;
                        break;
                    case R.id.radio_option2:
                        color1 = R.drawable.purple;
                        color2 = R.drawable.blue;
                }
            }
            else
            {   color1=R.drawable.white;
                color2=R.drawable.red;
            }
            int[] randomNumbers;
            randomColor=new Random();
            randomNumbers=new int[4];

            counter=0;
            //boolean flag=false;

            while(counter<4)
            {

                int number;
                boolean flag=false;
                number = randomColor.nextInt(16);
                for(int j = 0;j<4;j++)
                {
                    if(number == randomNumbers[j])
                    {
                        flag=true;
                        // /Toast.makeText(getApplicationContext(),""+flag,Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        flag=false;
                        //Toast.makeText(getApplicationContext(),"identical",Toast.LENGTH_SHORT).show();
                    }
                }
                if(!flag)
                {//Toast.makeText(getApplicationContext(),""+flag,Toast.LENGTH_SHORT).show();
                    randomNumbers[counter] = number;
                    counter++;
                }
                if(flag)
                {//Toast.makeText(getApplicationContext(),"Flag",Toast.LENGTH_SHORT).show();
                    counter--;
                }
            }
            gridArray[randomNumbers[0]].setColor(color1);
            gridArray[randomNumbers[1]].setColor(color1);
            gridArray[randomNumbers[2]].setColor(color2);
            gridArray[randomNumbers[3]].setColor(color2);

        }

    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView nextColorTxt=(TextView)findViewById(R.id.next_color_txt);
        initialColor();
        scorePref=getSharedPreferences(MySCORES, Context.MODE_PRIVATE);

        gridView=(GridView) findViewById(R.id.gridview);
        iAdapter=new ImageAdapter(this,gridArray);
        gridView.setAdapter(iAdapter);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                String color=" ";
                int c= gridArray[position].nextColor();
                ((ImageView)v).setImageResource(c);

                if(gridArray[position].getColor()==color2)
                {
                   switch (color2)
                    {
                        case  R.drawable.orange:
                           color="Next color blue";
                              break;
                        case R.drawable.blue2:
                            color="Next color orange";
                            break;
                        case R.drawable.purple:
                            color="Next color blue";
                            break;
                        case R.drawable.blue:
                            color="Next color purple";
                            break;
                        case R.drawable.red:
                            color="Next color white";
                            break;
                        case R.drawable.white:
                            color="Next color red";
                            break;
                    }
                    nextColorTxt.setText(color);
                }
                else if(gridArray[position].getColor()==color1)
                {
                    switch (color1)
                    {
                        case  R.drawable.orange:
                            color="Next color blue";
                            break;
                        case R.drawable.blue2:
                            color="Next color orange";
                            break;
                        case R.drawable.purple:
                            color="Next color blue";
                            break;
                        case R.drawable.blue:
                            color="Next color purple";
                            break;
                        case R.drawable.red:
                            color="Next color white";
                            break;
                        case R.drawable.white:
                            color="Next color red";
                            break;
                    }
                    nextColorTxt.setText(color);
                }
                comparePositions(position);
//Toast.makeText(GameScreen.this,""+position,Toast.LENGTH_SHORT).show();
            }
        });
        mResetGameButton=(Button)findViewById(R.id.reset_game_btn);
        mResetGameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //initialColor();
                cancel();
               startActivity(new Intent(getApplicationContext(),GameScreen.class));
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
    //this method compares the positions of the grid to avoid three squares with the same color on a row
   public void comparePositions(int position)
    {
         click++;
         boolean gameOver=false;

        if((gridChoice=Settings.getGridOption())>0)
        {
            switch (gridChoice) {
            //compare positions in a four by four grid
                case R.id.gridFourByFour:


                    if ((gridArray[0].getColor() == color1) && (gridArray[1].getColor() == color1) && (gridArray[2].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[0].getColor() == color1) && (gridArray[4].getColor() == color1) && (gridArray[8].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[1].getColor() == color1) && (gridArray[2].getColor() == color1) && (gridArray[3].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[1].getColor() == color1) && (gridArray[5].getColor() == color1) && (gridArray[9].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[2].getColor() == color1) && (gridArray[6].getColor() == color1) && (gridArray[10].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[3].getColor() == color1) && (gridArray[7].getColor() == color1) && (gridArray[11].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[4].getColor() == color1) && (gridArray[8].getColor() == color1) && (gridArray[12].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[4].getColor() == color1) && (gridArray[5].getColor() == color1) && (gridArray[6].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[5].getColor() == color1) && (gridArray[6].getColor() == color1) && (gridArray[7].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[5].getColor() == color1) && (gridArray[9].getColor() == color1) && (gridArray[13].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[6].getColor() == color1) && (gridArray[10].getColor() == color1) && (gridArray[14].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[7].getColor() == color1) && (gridArray[11].getColor() == color1) && (gridArray[15].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[8].getColor() == color1) && (gridArray[9].getColor() == color1) && (gridArray[10].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[9].getColor() == color1) && (gridArray[10].getColor() == color1) && (gridArray[11].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[12].getColor() == color1) && (gridArray[13].getColor() == color1) && (gridArray[14].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[13].getColor() == color1) && (gridArray[14].getColor() == color1) && (gridArray[15].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[0].getColor() == color2) && (gridArray[1].getColor() == color2) && (gridArray[2].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[0].getColor() == color2) && (gridArray[4].getColor() == color2) && (gridArray[8].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[1].getColor() == color2) && (gridArray[2].getColor() == color2) && (gridArray[3].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[1].getColor() == color2) && (gridArray[5].getColor() == color2) && (gridArray[9].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[2].getColor() == color2) && (gridArray[6].getColor() == color2) && (gridArray[10].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[3].getColor() == color2) && (gridArray[7].getColor() == color2) && (gridArray[11].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[4].getColor() == color2) && (gridArray[8].getColor() == color2) && (gridArray[12].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[4].getColor() == color2) && (gridArray[5].getColor() == color2) && (gridArray[6].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[5].getColor() == color2) && (gridArray[6].getColor() == color2) && (gridArray[7].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[5].getColor() == color2) && (gridArray[9].getColor() == color2) && (gridArray[13].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[6].getColor() == color2) && (gridArray[10].getColor() == color2) && (gridArray[14].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[7].getColor() == color2) && (gridArray[11].getColor() == color2) && (gridArray[15].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[8].getColor() == color2) && (gridArray[9].getColor() == color2) && (gridArray[10].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[9].getColor() == color2) && (gridArray[10].getColor() == color2) && (gridArray[11].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[12].getColor() == color2) && (gridArray[13].getColor() == color2) && (gridArray[14].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[13].getColor() == color2) && (gridArray[14].getColor() == color2) && (gridArray[15].getColor() == color2)) {
                        gameOver = true;
                    } else {
                        if (click == 12) {
                            Toast.makeText(getApplicationContext(), "You won"+timer.getText(), Toast.LENGTH_LONG).show();
                            cancel();


                        }

                    }
                    if (gameOver == true) {
                        Toast.makeText(getApplicationContext(), "Game Over - \"Three in a Row\" - You Lost", Toast.LENGTH_LONG).show();
                        gridView.setEnabled(false);
                        cancel();
                        //saveScore();
                        //desabilitar la cuadricula
                    } else {


                    }

                    break;
//               // compare positions in a five by five grid

                case R.id.gridFiveByFive:

                    if ((gridArray[0].getColor() == color1) && (gridArray[1].getColor() == color1) && (gridArray[2].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[1].getColor() == color1) && (gridArray[2].getColor() == color1) && (gridArray[3].getColor() == color1)) {
                        gameOver = true;
                    }
                    else if ((gridArray[2].getColor() == color1) && (gridArray[3].getColor() == color1) && (gridArray[4].getColor() == color1)) {
                        gameOver = true;
                    }else if ((gridArray[5].getColor() == color1) && (gridArray[6].getColor() == color1) && (gridArray[7].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[6].getColor() == color1) && (gridArray[7].getColor() == color1) && (gridArray[8].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[7].getColor() == color1) && (gridArray[8].getColor() == color1) && (gridArray[9].getColor() == color1)) {
                        gameOver = true;
                    }else if ((gridArray[10].getColor() == color1) && (gridArray[11].getColor() == color1) && (gridArray[12].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[11].getColor() == color1) && (gridArray[12].getColor() == color1) && (gridArray[13].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[12].getColor() == color1) && (gridArray[13].getColor() == color1) && (gridArray[14].getColor() == color1)) {
                        gameOver = true;
                    }else if ((gridArray[15].getColor() == color1) && (gridArray[16].getColor() == color1) && (gridArray[17].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[16].getColor() == color1) && (gridArray[17].getColor() == color1) && (gridArray[18].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[17].getColor() == color1) && (gridArray[18].getColor() == color1) && (gridArray[19].getColor() == color1)) {
                        gameOver = true;
                    }else if ((gridArray[20].getColor() == color1) && (gridArray[21].getColor() == color1) && (gridArray[22].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[21].getColor() == color1) && (gridArray[22].getColor() == color1) && (gridArray[23].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[22].getColor() == color1) && (gridArray[23].getColor() == color1) && (gridArray[24].getColor() == color1)) {
                        gameOver = true;
                    }else if ((gridArray[0].getColor() == color1) && (gridArray[5].getColor() == color1) && (gridArray[10].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[5].getColor() == color1) && (gridArray[10].getColor() == color1) && (gridArray[15].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[10].getColor() == color1) && (gridArray[15].getColor() == color1) && (gridArray[20].getColor() == color1)) {
                        gameOver = true;
                    }else if ((gridArray[1].getColor() == color1) && (gridArray[6].getColor() == color1) && (gridArray[11].getColor() == color1)) {
                        gameOver = true;
                    }
                    else if ((gridArray[6].getColor() == color1) && (gridArray[11].getColor() == color1) && (gridArray[16].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[11].getColor() == color1) && (gridArray[16].getColor() == color1) && (gridArray[21].getColor() == color1)) {
                        gameOver = true;
                    }else if ((gridArray[2].getColor() == color1) && (gridArray[7].getColor() == color1) && (gridArray[12].getColor() == color1)) {
                        gameOver = true;
                    }
                    else if ((gridArray[7].getColor() == color1) && (gridArray[12].getColor() == color1) && (gridArray[17].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[12].getColor() == color1) && (gridArray[17].getColor() == color1) && (gridArray[22].getColor() == color1)) {
                        gameOver = true;
                    }else if ((gridArray[3].getColor() == color1) && (gridArray[8].getColor() == color1) && (gridArray[13].getColor() == color1)) {
                        gameOver = true;
                    }
                    else if ((gridArray[8].getColor() == color1) && (gridArray[13].getColor() == color1) && (gridArray[18].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[13].getColor() == color1) && (gridArray[18].getColor() == color1) && (gridArray[23].getColor() == color1)) {
                        gameOver = true;
                    }else if ((gridArray[4].getColor() == color1) && (gridArray[9].getColor() == color1) && (gridArray[14].getColor() == color1)) {
                        gameOver = true;
                    }
                    else if ((gridArray[9].getColor() == color1) && (gridArray[14].getColor() == color1) && (gridArray[19].getColor() == color1)) {
                        gameOver = true;
                    } else if ((gridArray[14].getColor() == color1) && (gridArray[19].getColor() == color1) && (gridArray[24].getColor() == color1)) {
                        gameOver = true;
                    }
                    if ((gridArray[0].getColor() == color2) && (gridArray[1].getColor() == color2) && (gridArray[2].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[1].getColor() == color2) && (gridArray[2].getColor() == color2) && (gridArray[3].getColor() == color2)) {
                        gameOver = true;
                    }
                    else if ((gridArray[2].getColor() == color2) && (gridArray[3].getColor() == color2) && (gridArray[4].getColor() == color2)) {
                        gameOver = true;
                    }
                    else if ((gridArray[5].getColor() == color2) && (gridArray[6].getColor() == color2) && (gridArray[7].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[6].getColor() == color2) && (gridArray[7].getColor() == color2) && (gridArray[8].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[7].getColor() == color2) && (gridArray[8].getColor() == color2) && (gridArray[9].getColor() == color2)) {
                        gameOver = true;
                    }else if ((gridArray[10].getColor() == color2) && (gridArray[11].getColor() == color2) && (gridArray[12].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[11].getColor() == color2) && (gridArray[12].getColor() == color2) && (gridArray[13].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[12].getColor() == color2) && (gridArray[13].getColor() == color2) && (gridArray[14].getColor() == color2)) {
                        gameOver = true;
                    }else if ((gridArray[15].getColor() == color2) && (gridArray[16].getColor() == color2) && (gridArray[17].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[16].getColor() == color2) && (gridArray[17].getColor() == color2) && (gridArray[18].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[17].getColor() == color2) && (gridArray[18].getColor() == color2) && (gridArray[19].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[20].getColor() == color2) && (gridArray[21].getColor() == color2) && (gridArray[22].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[21].getColor() == color2) && (gridArray[22].getColor() == color2) && (gridArray[23].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[22].getColor() == color2) && (gridArray[23].getColor() == color2) && (gridArray[24].getColor() == color2)) {
                        gameOver = true;
                    }else if ((gridArray[0].getColor() == color2) && (gridArray[5].getColor() == color2) && (gridArray[10].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[5].getColor() == color2) && (gridArray[10].getColor() == color2) && (gridArray[15].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[10].getColor() == color2) && (gridArray[15].getColor() == color2) && (gridArray[20].getColor() == color2)) {
                        gameOver = true;
                    }else if ((gridArray[1].getColor() == color2) && (gridArray[6].getColor() == color2) && (gridArray[11].getColor() == color2)) {
                        gameOver = true;
                    }
                    else if ((gridArray[6].getColor() == color2) && (gridArray[11].getColor() == color2) && (gridArray[16].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[11].getColor() == color2) && (gridArray[16].getColor() == color2) && (gridArray[21].getColor() == color2)) {
                        gameOver = true;
                    }else if ((gridArray[2].getColor() == color2) && (gridArray[7].getColor() == color2) && (gridArray[12].getColor() == color2)) {
                        gameOver = true;
                    }
                    else if ((gridArray[7].getColor() == color2) && (gridArray[12].getColor() == color2) && (gridArray[17].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[12].getColor() == color2) && (gridArray[17].getColor() == color2) && (gridArray[22].getColor() == color2)) {
                        gameOver = true;
                    }else if ((gridArray[3].getColor() == color2) && (gridArray[8].getColor() == color2) && (gridArray[13].getColor() == color2)) {
                        gameOver = true;
                    }
                    else if ((gridArray[8].getColor() == color2) && (gridArray[13].getColor() == color2) && (gridArray[18].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[13].getColor() == color2) && (gridArray[18].getColor() == color2) && (gridArray[23].getColor() == color2)) {
                        gameOver = true;
                    }else if ((gridArray[4].getColor() == color2) && (gridArray[9].getColor() == color2) && (gridArray[14].getColor() == color2)) {
                        gameOver = true;
                    }
                    else if ((gridArray[9].getColor() == color2) && (gridArray[14].getColor() == color2) && (gridArray[19].getColor() == color2)) {
                        gameOver = true;
                    } else if ((gridArray[14].getColor() == color2) && (gridArray[19].getColor() == color2) && (gridArray[24].getColor() == color2)) {
                        gameOver = true;
                    } else {
                        if (click == 21) {
                            Toast.makeText(getApplicationContext(), "You won", Toast.LENGTH_LONG).show();
                            cancel();
                            //saveScore();
                        }

                    }
                    if (gameOver == true) {
                        Toast.makeText(getApplicationContext(), "Game Over - \"Three in a Row\" - You Lost", Toast.LENGTH_LONG).show();
                        gridView.setEnabled(false);
                        cancel();

                    } else {


                    }
                    break;
                }
            }
            else{
                 //default grid size position comparison

                if ((gridArray[0].getColor() == color1) && (gridArray[1].getColor() == color1) && (gridArray[2].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[0].getColor() == color1) && (gridArray[4].getColor() == color1) && (gridArray[8].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[1].getColor() == color1) && (gridArray[2].getColor() == color1) && (gridArray[3].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[1].getColor() == color1) && (gridArray[5].getColor() == color1) && (gridArray[9].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[2].getColor() == color1) && (gridArray[6].getColor() == color1) && (gridArray[10].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[3].getColor() == color1) && (gridArray[7].getColor() == color1) && (gridArray[11].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[4].getColor() == color1) && (gridArray[8].getColor() == color1) && (gridArray[12].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[4].getColor() == color1) && (gridArray[5].getColor() == color1) && (gridArray[6].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[5].getColor() == color1) && (gridArray[6].getColor() == color1) && (gridArray[7].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[5].getColor() == color1) && (gridArray[9].getColor() == color1) && (gridArray[13].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[6].getColor() == color1) && (gridArray[10].getColor() == color1) && (gridArray[14].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[7].getColor() == color1) && (gridArray[11].getColor() == color1) && (gridArray[15].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[8].getColor() == color1) && (gridArray[9].getColor() == color1) && (gridArray[10].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[9].getColor() == color1) && (gridArray[10].getColor() == color1) && (gridArray[11].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[12].getColor() == color1) && (gridArray[13].getColor() == color1) && (gridArray[14].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[13].getColor() == color1) && (gridArray[14].getColor() == color1) && (gridArray[15].getColor() == color1)) {
                    gameOver = true;
                } else if ((gridArray[0].getColor() == color2) && (gridArray[1].getColor() == color2) && (gridArray[2].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[0].getColor() == color2) && (gridArray[4].getColor() == color2) && (gridArray[8].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[1].getColor() == color2) && (gridArray[2].getColor() == color2) && (gridArray[3].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[1].getColor() == color2) && (gridArray[5].getColor() == color2) && (gridArray[9].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[2].getColor() == color2) && (gridArray[6].getColor() == color2) && (gridArray[10].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[3].getColor() == color2) && (gridArray[7].getColor() == color2) && (gridArray[11].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[4].getColor() == color2) && (gridArray[8].getColor() == color2) && (gridArray[12].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[4].getColor() == color2) && (gridArray[5].getColor() == color2) && (gridArray[6].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[5].getColor() == color2) && (gridArray[6].getColor() == color2) && (gridArray[7].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[5].getColor() == color2) && (gridArray[9].getColor() == color2) && (gridArray[13].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[6].getColor() == color2) && (gridArray[10].getColor() == color2) && (gridArray[14].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[7].getColor() == color2) && (gridArray[11].getColor() == color2) && (gridArray[15].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[8].getColor() == color2) && (gridArray[9].getColor() == color2) && (gridArray[10].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[9].getColor() == color2) && (gridArray[10].getColor() == color2) && (gridArray[11].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[12].getColor() == color2) && (gridArray[13].getColor() == color2) && (gridArray[14].getColor() == color2)) {
                    gameOver = true;
                } else if ((gridArray[13].getColor() == color2) && (gridArray[14].getColor() == color2) && (gridArray[15].getColor() == color2)) {
                    gameOver = true;
                } else {
                    if (click == 12) {
                        Toast.makeText(getApplicationContext(), "You won", Toast.LENGTH_LONG).show();
                        cancel();
                    }

                }
                if (gameOver == true) {
                    Toast.makeText(getApplicationContext(), "Game Over - \"Three in a Row\" - You Lost", Toast.LENGTH_LONG).show();
                    gridView.setEnabled(false);
                    cancel();
                    //saveScore();
                    //desabilitar la cuadricula
                } else {


                }
        }
    }
    // method to save the high score
   /* public void saveScore()
    {

       chronometer=Integer.parseInt(timer.getText().toString());
        int val=scorePref.getInt(SCORE, 100);


        if(scorePref.contains(SCORE)) {
            if (val > chronometer) {
                SharedPreferences.Editor editor = scorePref.edit();
                editor.putInt(SCORE, chronometer);
                editor.commit();
                Toast.makeText(GameScreen.this,"Scored saved",Toast.LENGTH_SHORT).show();

            }
        }
        //si no contiene  ningun socore esto solo para la primera vez
        else
        {
            SharedPreferences.Editor editor = scorePref.edit();
            editor.putInt(SCORE, chronometer);
            editor.commit();
        }
    }*/
    /*
    //method to get the high score
    public void getScore()
    {
        TextView score=(TextView)findViewById(R.id.score_message);

        if(scorePref.contains(SCORE)){
            score.setText("High Score"+scorePref.getInt(SCORE,100));
            //el 100 significa el default value

        }

    }*/
    //this method starts the timer
    public void start()
    {
        timer=(TextView)findViewById(R.id.timer);
        if((levelChoice=Settings.getLevel())>0)
        {
            switch (levelChoice)
            {
                case R.id.radDificult:
                    timer.setText("30");
                    mCountDownTimer=new CountDownTimer(30*1000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timer.setText(" "+millisUntilFinished/1000);
                    }

                    @Override
                    public void onFinish() {
                        timer.setText("Opps! time is over");
                    }

                    };

                break;
                case R.id.radEasy:
                    timer.setText("60");
                    mCountDownTimer=new CountDownTimer(60*1000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timer.setText(" "+ millisUntilFinished/1000);
                        }

                        @Override
                        public void onFinish() {
                            timer.setText("Opps! time is over");
                            gridView.setEnabled(false);
                        }
                    };
                break;
            }

        }
        else{
        timer.setText("60");
        mCountDownTimer=new CountDownTimer(60*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(" "+ millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timer.setText("Opps! time is over");
                gridView.setEnabled(false);
            }
        };}
        mCountDownTimer.start();

    }
    public void cancel()
    {
      if(mCountDownTimer!=null){
          mCountDownTimer.cancel();
          mCountDownTimer=null;
      }
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
            case R.id.content_settings:
                i= new Intent(this, Settings.class);
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
