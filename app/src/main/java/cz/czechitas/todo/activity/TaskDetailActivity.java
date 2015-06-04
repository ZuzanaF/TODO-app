package cz.czechitas.todo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cz.czechitas.todo.R;
import cz.czechitas.todo.database.dao.TaskDAO;
import cz.czechitas.todo.entity.TaskEntity;


public class TaskDetailActivity extends AppCompatActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_detail);
		setupActionBar();
		bindData();
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// action bar menu behaviour
		switch(item.getItemId())
		{
			case android.R.id.home:
				finish();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}


	private void setupActionBar()
	{
		ActionBar bar = getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
	}


	private void bindData()
	{
		// reference
		final EditText titleEditText = (EditText) findViewById(R.id.title);
		final DatePicker datePicker = (DatePicker) findViewById(R.id.date);
		final Button button = (Button) findViewById(R.id.button);

		// button
		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String title = titleEditText.getText().toString();
				Calendar calendar = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), 0, 0); // selected date at 0:00
				Date selectedDate = calendar.getTime();

				calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				Date startOfToday = calendar.getTime();

				if(title.trim().isEmpty())
				{
					Toast.makeText(TaskDetailActivity.this, R.string.activity_task_detail_validation_title, Toast.LENGTH_LONG).show();
				}
				else if(selectedDate.before(startOfToday))
				{
					Toast.makeText(TaskDetailActivity.this, R.string.activity_task_detail_validation_date, Toast.LENGTH_LONG).show();
				}
				else
				{
					createTask(title, selectedDate);
					finishActivity();
				}
			}
		});
	}


	private void createTask(String title, Date date)
	{
		TaskEntity task = new TaskEntity();
		task.setTitle(title);
		task.setDate(date);
		task.setStatus(false);

		TaskDAO dao = new TaskDAO();
		dao.create(task);
	}


	private void finishActivity()
	{
		Intent resultIntent = new Intent();
		setResult(RESULT_OK, resultIntent);
		finish();
	}
}
