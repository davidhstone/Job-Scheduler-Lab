package com.example.davidstone.job_scheduler_lab;

/**
 * Created by davidstone on 8/12/16.
 */
public class DataSingleton {

    private String myText;

    public interface TextChangeListener {
        void onTextChanged (String oldText);
    }

    private TextChangeListener TextChangeListener;

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
        TextChangeListener = listener;
    }

    private void notifyDataChanged(String text){
        if(TextChangeListener != null) {
            TextChangeListener.onTextChanged(text);
        }
    }

    public void updateMyText(String text){
        String oldText = myText;
        myText = text;
        notifyDataChanged(oldText);

    }

    public String getMyText(){
        return myText;
    }
}
