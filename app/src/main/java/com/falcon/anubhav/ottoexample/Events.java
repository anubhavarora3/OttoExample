package com.falcon.anubhav.ottoexample;

/**
 * Created by anubhav on 28/09/17.
 */

public class Events {

    public static class FragmentActivityMessage {
        private String message;

        public FragmentActivityMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class ActivityFragmentMessage {
        private String message;

        public ActivityFragmentMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}