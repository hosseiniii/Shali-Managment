package ir.hosseini.shalimanagment.model;

import androidx.annotation.DrawableRes;

import ir.hosseini.shalimanagment.Interfaces.AlertDialogListener;
import ir.hosseini.shalimanagment.R;

public class DialogObject {

    String title;
    String message;
    String positive;
    String negative;

    Boolean positiveDisplay;
    Boolean negativeDisplay;

    @DrawableRes int positiveIcon = R.drawable.ic_check;
    @DrawableRes int negativeIcon = R.drawable.ic_close;

    AlertDialogListener alertDialogListener;

    public String getTitle() {
        return title;
    }

    public DialogObject setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public DialogObject setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getPositive() {
        return positive;
    }

    public DialogObject setPositive(String positive) {
        this.positive = positive;
        return this;
    }

    public String getNegative() {
        return negative;
    }

    public DialogObject setNegative(String negative) {
        this.negative = negative;
        return this;
    }

    public Boolean getPositiveDisplay() {
        return positiveDisplay;
    }

    public DialogObject setPositiveDisplay(Boolean positiveDisplay) {
        this.positiveDisplay = positiveDisplay;
        return this;
    }

    public Boolean getNegativeDisplay() {
        return negativeDisplay;
    }

    public DialogObject setNegativeDisplay(Boolean negativeDisplay) {
        this.negativeDisplay = negativeDisplay;
        return this;
    }

    public int getPositiveIcon() {
        return positiveIcon;
    }

    public DialogObject setPositiveIcon(@DrawableRes int positiveIcon) {
        this.positiveIcon = positiveIcon;
        return this;
    }

    public int getNegativeIcon() {
        return negativeIcon;
    }

    public DialogObject setNegativeIcon(@DrawableRes int negativeIcon) {
        this.negativeIcon = negativeIcon;
        return this;
    }

    public DialogObject setAlertDialogListener(AlertDialogListener alertDialogListener) {
        this.alertDialogListener = alertDialogListener;
        return this;
    }

    public AlertDialogListener getAlertDialogListener() {
        return alertDialogListener;
    }
}
