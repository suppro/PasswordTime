package com.company.pass;

class PassList {
    private int Id;
    private String Password;
    private long BestTry;

    public PassList(String Password){
        this.Password = Password;
        //this.BestTry = BestTry;
    }
    public String getPassword() {
        return  Password;
    }
    public long getBestTry() {
        return  BestTry;
    }
}
