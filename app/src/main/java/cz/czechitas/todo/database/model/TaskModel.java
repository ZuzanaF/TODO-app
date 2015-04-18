package cz.czechitas.todo.database.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

import cz.czechitas.todo.entity.TaskEntity;


@Table(name = "Tasks")
public class TaskModel extends Model
{
	@Column(name = "Title") public String title;
	@Column(name = "Date") public Date date;
	@Column(name = "Status") public boolean status;


	// empty constructor
	public TaskModel()
	{
		super();
	}


	public TaskEntity toEntity()
	{
		TaskEntity e = new TaskEntity();
		e.setId(getId());
		e.setTitle(title);
		e.setDate(date);
		e.setStatus(status);
		return e;
	}


	public void set(TaskEntity e)
	{
		title = e.getTitle();
		date = e.getDate();
		status = e.isStatus();
	}
}
