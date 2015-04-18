package cz.czechitas.todo.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cz.czechitas.todo.R;
import cz.czechitas.todo.adapter.TaskListAdapter;
import cz.czechitas.todo.database.dao.TaskDAO;
import cz.czechitas.todo.entity.TaskEntity;


public class TaskListActivity extends ActionBarActivity
{
	private static final int REQUEST_FOR_ADD_TASK = 0;

	private TaskListAdapter mAdapter;
	private List<TaskEntity> mList = new ArrayList<>();


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_list);
		setupActionBar();
		loadData();
		bindData();
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode==REQUEST_FOR_ADD_TASK)
		{
			if(resultCode==RESULT_OK)
			{
				loadData();
				bindData();
			}
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// action bar menu
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_task_list, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// action bar menu behaviour
		switch(item.getItemId())
		{
			case R.id.menu_add:
				startTaskDetail();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}


	private void setupActionBar()
	{
		ActionBar bar = getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(false);
	}


	private void loadData()
	{
		// clear list
		mList.clear();

		// load tasks
		TaskDAO dao = new TaskDAO();
		mList = dao.readAll();
	}


	private void bindData()
	{
		// reference
		ListView listView = (ListView) findViewById(R.id.list);
		ViewGroup emptyViewGroup = (ViewGroup) findViewById(R.id.empty);

		// adapter
		if(mAdapter==null)
		{
			// create adapter
			mAdapter = new TaskListAdapter(this, mList);
		}
		else
		{
			// refresh adapter
			mAdapter.refill(this, mList);
		}

		// set adapter in listview
		listView.setAdapter(mAdapter);

		// long click
		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
		{
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
			{
				showDeleteDialog(position);
				return true;
			}
		});

		// empty view
		listView.setEmptyView(emptyViewGroup);
	}


	private void startTaskDetail()
	{
		Intent intent = new Intent(this, TaskDetailActivity.class);
		startActivityForResult(intent, REQUEST_FOR_ADD_TASK);
	}


	private void showDeleteDialog(final int position)
	{
		new AlertDialog.Builder(this)
				.setTitle(R.string.activity_task_list_delete_title)
				.setMessage(R.string.activity_task_list_delete_message)
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						deleteTask(position);
						mList.remove(position);
						bindData();
					}
				})
				.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						// do nothing
					}
				})
				.show();
	}


	private void deleteTask(int position)
	{
		long taskId = mList.get(position).getId();
		TaskDAO dao = new TaskDAO();
		dao.delete(taskId);
	}
}
