package com.example.android.transcripthub;
public class Message{
    private String name;
    private  int type;



    public Message(String name, int type) {
        this.name = name;
        this.type = type;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType(){
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Message)) {
            return false;
        }

        Message p = (Message) other;
        try {
            if(this.name.equals(p.name) && this.type == p.type)
                return true;

        } catch (Exception e) {
            return false;
        }
        return false;
    }


}