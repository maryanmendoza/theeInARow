package au.edu.holmesglen.maryan.threeinarow;

/**
 * Created by maryanmendozanunez on 31/10/16.
 */

public class Item {
    private int colorImg;
    static int click = 1;
    static int color1;
    static int color2;

    public Item(int colImg, String title) {
        this.setColor(colImg);
    }

    public int getColor() {
        return colorImg;
    }

    public void setColor(int rDC) {
        colorImg = rDC;

    }

    public int nextColor()
    {
        color1=R.drawable.white;
        color2=R.drawable.red;

        int colorChoice;

        if(!((colorChoice= Settings.getValues())>0))
        {
            color1=R.drawable.white;
            color2=R.drawable.red;
        }
        else
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

        //colorImg=getColor();

        if(colorImg==R.drawable.grey)
        {
            if(click==1)
            {
                colorImg=color1;
                click=2;
            }
            else //if(click==2)
            {
                colorImg=color2;
                click=1;
            }
        }
        return colorImg;
    }


}
