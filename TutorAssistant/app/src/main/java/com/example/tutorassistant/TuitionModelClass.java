package com.example.tutorassistant;

public class TuitionModelClass {

String Name,Amount,Payment,Classes ;

    public TuitionModelClass(String name, String amount, String payment, String classes) {
        Name = name;
        Amount = amount;
        Payment = payment;
        Classes = classes;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getPayment() {
        return Payment;
    }

    public void setPayment(String payment) {
        Payment = payment;
    }

    public String getClasses() {
        return Classes;
    }

    public void setClasses(String classes) {
        Classes = classes;
    }
}
