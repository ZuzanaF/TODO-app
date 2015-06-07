package cz.czechitas.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import cz.czechitas.todo.R;
import cz.czechitas.todo.database.dao.TaskDAO;
import cz.czechitas.todo.entity.TaskEntity;
import cz.czechitas.todo.utility.DateConvertor;


public class TaskListAdapter extends BaseAdapter
{
	private Context mContext;
	private List<TaskEntity> mTaskList;


	public TaskListAdapter(Context context, List<TaskEntity> taskList)
	{
		mContext = context;
		mTaskList = taskList;
	}
	
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		// inflate view
		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.activity_task_list_item, parent, false);
			
			// view holder
			ViewHolder holder = new ViewHolder();
			holder.titleTextView = (TextView) convertView.findViewById(R.id.title);
			holder.dateTextView = (TextView) convertView.findViewById(R.id.date);
			holder.statusCheckBox = (CheckBox) convertView.findViewById(R.id.status);
			convertView.setTag(holder);
		}
		
		// entity
		final TaskEntity task = mTaskList.get(position);
		
		if(task != null)
		{
			// view holder
			ViewHolder holder = (ViewHolder) convertView.getTag();
			
			// content
			holder.statusCheckBox.setChecked(task.isStatus());
			holder.titleTextView.setText(task.getTitle());
			holder.dateTextView.setText(DateConvertor.dateToString(task.getDate(), "d. M. yyyy"));

			// checkbox
			holder.statusCheckBox.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View view)
				{
					// is checkbox checked
					boolean isChecked = ((CheckBox) view).isChecked();

					// status has changed
					if(task.isStatus()!=isChecked)
					{
						task.setStatus(isChecked);
						updateTask(task);
					}
				}
			});
		}
		
		return convertView;
	}
	
	
	@Override
	public int getCount()
	{
		if(mTaskList!=null) return mTaskList.size();
		else return 0;
	}
	
	
	@Override
	public Object getItem(int position)
	{
		if(mTaskList!=null) return mTaskList.get(position);
		else return null;
	}
	
	
	@Override
	public long getItemId(int position)
	{
		return position;
	}


	public void refill(Context context, List<TaskEntity> taskList)
	{
		mContext = context;
		mTaskList = taskList;
		notifyDataSetChanged();
	}


	private void updateTask(TaskEntity task)
	{
		TaskDAO dao = new TaskDAO();
		dao.update(task);
	}
	
	
	static class ViewHolder
	{
		CheckBox statusCheckBox;
		TextView titleTextView;
		TextView dateTextView;
	}
}
