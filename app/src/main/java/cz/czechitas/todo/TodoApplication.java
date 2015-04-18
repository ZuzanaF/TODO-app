package cz.czechitas.todo;

import android.app.Application;

import com.activeandroid.ActiveAndroid;


public class TodoApplication extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();

		// initialize database
		ActiveAndroid.initialize(this);
	}
}
