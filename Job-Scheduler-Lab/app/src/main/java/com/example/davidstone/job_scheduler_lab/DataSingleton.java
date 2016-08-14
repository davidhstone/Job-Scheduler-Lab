package com.example.davidstone.job_scheduler_lab;

/**
 * Created by davidstone on 8/12/16.
 */
public class DataSingleton {

    private String myText;
    private String myText2;

    public interface TextChangeListener {
        void onTextChanged (String oldText, String newText);
    }

    private TextChangeListener textChangeListener;

    private static DataSingleton instance;

    private DataSingleton(){
    }
    public static DataSingleton getInstance(){
        if(instance == null){
            instance = new DataSingleton();
        }
        return instance;
    }

    public void setListener(TextChangeListener listener){
        textChangeListener = listener;
    }

    private void notifyDataChanged(String text, String text2){
        if(textChangeListener != null) {
            textChangeListener.onTextChanged(text, text2);
        }
    }

    public void updateMyText(String text, String text2){
        String oldText = "The member being replaced is " + myText;
        myText = text;
        String newText = "The new member is " + myText2;
        myText2 = text2;
        notifyDataChanged(oldText, newText);

    }

    public String getMyText(){
        return myText;
    }

    public String getMyText2() { return myText2; }
}
