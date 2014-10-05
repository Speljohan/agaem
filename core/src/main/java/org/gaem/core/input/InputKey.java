package org.gaem.core.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by Joar on 2014-10-05.
 */
public class InputKey {

    private int keyCode;
    private boolean hasJustPressed;
    private boolean isPressingRightNow;
    private boolean hasJustReleased;
    private boolean oldInput;


    public InputKey(int keyCode) {
        this.keyCode = keyCode;
        hasJustPressed = false;
        isPressingRightNow = false;
        oldInput = false;
        hasJustReleased = false;
    }

    public void update(float delta){

        hasJustPressed = false;
        isPressingRightNow = false;
        hasJustReleased = false;

        boolean newInput = Gdx.input.isKeyPressed(keyCode);

        if(newInput == true)
        {
           if(oldInput == false){
               hasJustPressed = true;
           }
            isPressingRightNow = true;
        }

        if(newInput == false){
            if(oldInput == true){
               hasJustReleased = true;
            }
        }


        oldInput = newInput;
    }

    public boolean justPressed(){
        return hasJustPressed;
    }

    public boolean justReleased(){
        return  hasJustReleased;

    }

    public boolean isPressing(){
        return  isPressingRightNow;
    }
}
