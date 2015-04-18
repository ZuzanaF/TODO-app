package cz.czechitas.todo.entity;


import java.util.Date;


public class TaskEntity
{
	private long id;
	private String title;
	private Date date;
	private boolean status;


	// empty constructor
	public TaskEntity()
	{

	}


	public long getId()
	{
		return id;
	}


	public void setId(long id)
	{
		this.id = id;
	}


	public String getTitle()
	{
		return title;
	}


	public void setTitle(String title)
	{
		this.title = title;
	}


	public Date getDate()
	{
		return date;
	}


	public void setDate(Date date)
	{
		this.date = date;
	}


	public boolean isStatus()
	{
		return status;
	}


	public void setStatus(boolean status)
	{
		this.status = status;
	}
}
