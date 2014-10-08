package org.gaem.core.ui.dialogue;

import java.util.ArrayList;

/**
 * Created by Joar on 2014-10-08.
 */
public class DialogueItem {
    private String ID;
    private String text;
    private String linkedID;
    private ArrayList<DialogueChoiceItem> choices;


    public void setChoices(ArrayList<DialogueChoiceItem> choices) {
        this.choices = choices;
    }



    public DialogueItem(String ID, String text, String linkedID,ArrayList<DialogueChoiceItem> item) {
        this.ID = ID;
        this.text = text;
        this.linkedID = linkedID;
        this.choices = item;
    }

    public String getID() {
        return ID;
    }

    public String getText() {
        return text;
    }

    public String getLinkedID() {
        return linkedID;
    }

    public ArrayList<DialogueChoiceItem> getChoices() {
        return choices;
    }
}
