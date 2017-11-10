package com.zhao.shopapp.commont;

import android.app.Activity;

import java.util.Stack;

/**
 * Activity管理者
 */

public class AppManager {
    private static AppManager appManager=null;

    private Stack<Activity> activityStack=new Stack<>();
    private AppManager(){}

    public static AppManager getInstance()
    {
        if(appManager==null)
        {
            appManager=new AppManager();
        }
        return appManager;
    }

    public void addActivity(Activity activity)
    {
        activityStack.add(activity);
    }

    public void removeCurrent()
    {
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }

    public void remove(Activity activity)
    {
        for (int i = activityStack.size()-1; i > 0; i--) {
            Activity activity1 = activityStack.get(i);
            if(activity1.getClass().equals(activity.getClass()))
            {
                activity1.finish();
                activityStack.remove(activity1);
            }
        }
    }

    public void removeAll()
    {
        for (int i = activityStack.size() - 1; i > 0; i--) {
            Activity activity = activityStack.get(i);
            activity.finish();
            activityStack.remove(activity);
        }
    }

    public int getSize()
    {
        return activityStack.size();
    }
}
